/*CREATE DATABASE areoporto;
c aereoporto*/
DROP TABLE IF EXISTS Pilota CASCADE;
DROP TABLE IF EXISTS Hostess CASCADE;
DROP TABLE IF EXISTS Cliente CASCADE;
DROP TABLE IF EXISTS Aereo CASCADE;
DROP TABLE IF EXISTS Volo CASCADE;
DROP TABLE IF EXISTS Prenotazione CASCADE;
DROP TRIGGER IF EXISTS trgOverbooking ON Prenotazione;
DROP TRIGGER IF EXISTS trg_checkPostoPrenotazione ON Prenotazione;
DROP FUNCTION IF EXISTS checkOverbooking();
DROP FUNCTION IF EXISTS checkPostoPrenotazione();

CREATE TABLE Pilota(

	login				VARCHAR(30) NOT NULL UNIQUE ,
	password			VARCHAR(60) NOT NULL,
	nomeCompleto		VARCHAR(30) NOT NULL,
	codiceFiscale		CHAR(16)	NOT NULL,
	numeroCellulare		CHAR(13),
	idPilota			TEXT		NOT NULL PRIMARY KEY,
	salario				REAL		NOT NULL,

	CONSTRAINT passwdlenght CHECK (length(password) >= 8),
	CONSTRAINT CodiceFiscaleLenght CHECK (length(codiceFiscale) = 16),
	CONSTRAINT salarioPositivo CHECK (salario > 0)
);
CREATE TABLE Hostess(

	login				VARCHAR(30) NOT NULL UNIQUE,
	password			VARCHAR(60) NOT NULL,
	nomeCompleto		VARCHAR(30) NOT NULL,
	codiceFiscale		CHAR(16)	NOT NULL,
	numeroCellulare		CHAR(13),
	idHostess			TEXT		NOT NULL PRIMARY KEY,
	salario				REAL		NOT NULL,

	CONSTRAINT passwdlenght CHECK (length(password) >= 8),
    CONSTRAINT CodiceFiscaleLenght CHECK (length(codiceFiscale)=16),
	CONSTRAINT salarioPositivo CHECK (salario > 0)
);
CREATE TABLE Cliente(

	login				VARCHAR(30) NOT NULL UNIQUE,
	password			VARCHAR(60) NOT NULL,
	nomeCompleto		VARCHAR(30) NOT NULL,
	codiceFiscale		CHAR(16)	NOT NULL,
	numeroCellulare		CHAR(13),
	idCliente			TEXT		NOT NULL PRIMARY KEY,

	CONSTRAINT passwdlenght CHECK (length(password) >= 8),
	CONSTRAINT CodiceFiscaleLenght CHECK (length(codiceFiscale) = 16)
);
CREATE TABLE Aereo(
	idAereo				TEXT		NOT NULL PRIMARY KEY,
	modello				VARCHAR(30) NOT NULL,
	nPosti				INTEGER     NOT NULL,
	CONSTRAINT nPostiPositivo CHECK (nPosti > 0) 
);
CREATE TABLE Volo(
	idVolo				TEXT 		NOT NULL PRIMARY KEY,
	destinazione 		VARCHAR(30) NOT NULL,
	durata 				INTEGER 	NOT NULL,
	idPilota 			TEXT		NOT NULL,
	idCoPilota			TEXT		NOT NULL,
	idHostess1			TEXT 		NOT NULL,
	idHostess2			TEXT 		NOT NULL,
	idAereo				TEXT		NOT NULL,
	CONSTRAINT FK_PILOTA FOREIGN KEY (idPilota) REFERENCES Pilota(idPilota)
		ON DELETE CASCADE,
	CONSTRAINT FK_COPILOTA FOREIGN KEY (idCoPilota) REFERENCES Pilota(idPilota)
		ON DELETE CASCADE,
	CONSTRAINT FK_HOSTESS1 FOREIGN KEY (idHostess1) REFERENCES Hostess(idHostess)
		ON DELETE CASCADE,
	CONSTRAINT FK_HOSTESS2 FOREIGN KEY (idHostess2) REFERENCES Hostess(idHostess)
		ON DELETE CASCADE,
	CONSTRAINT FK_AEREO FOREIGN KEY (idAereo) REFERENCES Aereo(idAereo)
		ON DELETE CASCADE,
	CONSTRAINT LAVORATORI_DIVERSI CHECK (NOT ((idPilota = idCopilota) OR (idHostess1 = idHostess2))),
	CONSTRAINT durata_reale CHECK (durata > 0) 
);
CREATE TABLE Prenotazione(
	idPrenotazione 		TEXT 		NOT NULL PRIMARY KEY,
	idCliente			TEXT		NOT NULL,
	idVolo				TEXT		NOT NULL,
	posto				CHAR(3)		NOT NULL, --Posto non puo essere unique altrimenti l'overbooking non avrebbe senso
	classe				VARCHAR(15) NOT NULL,
	CONSTRAINT FK_CLIENTE FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente)
		ON DELETE CASCADE,

	CONSTRAINT FK_VOLO FOREIGN KEY (idVolo) REFERENCES VOLO(idVolo)
		ON DELETE CASCADE,
	CONSTRAINT ClasseENUM CHECK (classe IN ('PRIMA','ECONOMY','ECONOMYPLUS','BUSINESS'))
);

CREATE OR REPLACE FUNCTION checkOverbooking()
RETURNS TRIGGER AS $$
DECLARE
    numeroPostiPrenotati INTEGER;
    numeroPostiAereo INTEGER;
BEGIN

    --Numero Posti Prenotati
    SELECT COUNT(IdPrenotazione) INTO numeroPostiPrenotati
    FROM Prenotazione
    WHERE NEW.idVolo = Prenotazione.idVolo;

    SELECT Aereo.nPosti INTO numeroPostiAereo
    FROM Volo
    JOIN Aereo
        ON Aereo.idAereo = Volo.idAereo
    WHERE Volo.idVolo = NEW.idVolo;


    IF (numeroPostiPrenotati + 1 > ( CAST(NumeroPostiAereo AS REAL) * 1.10 ) ) THEN
       RAISE EXCEPTION 'Aereo Pienamente Prenotato!
       Posti Totali Aereo : %
       Posti gia Prenotati: %', numeroPostiAereo,numeroPostiPrenotati;
    END IF;

    RETURN NEW;

END;
$$ LANGUAGE 'plpgsql';


CREATE TRIGGER trgOverbooking
BEFORE INSERT ON Prenotazione
FOR EACH ROW
EXECUTE FUNCTION checkOverbooking();

CREATE OR REPLACE FUNCTION checkPostoPrenotazione()
    RETURNS TRIGGER AS $$
DECLARE
    numeroStessoClientePosto INTEGER;
    numeroTotalePosto INTEGER;
BEGIN
    -- A) stesso cliente non può prenotare lo stesso posto due vol1te sullo stesso volo
    SELECT COUNT(*) INTO numeroStessoClientePosto
    FROM Prenotazione
    WHERE idVolo = NEW.idVolo
      AND posto = NEW.posto
      AND idCliente = NEW.idCliente;

    IF numeroStessoClientePosto >= 1 THEN
        RAISE EXCEPTION 'Il cliente ha gia'' prenotato questo posto su questo volo';
    END IF;

    -- B) lo stesso posto può essere prenotato al massimo 2 volte sullo stesso volo
    SELECT COUNT(*) INTO numeroTotalePosto
    -- Conta quante volte lo stesso posto è già stato prenotato per questo volo
    FROM Prenotazione
    WHERE idVolo = NEW.idVolo
      AND posto = NEW.posto;

    IF numeroTotalePosto >= 2 THEN
        RAISE EXCEPTION 'Posto % gia'' prenotato il numero massimo di volte su questo volo', NEW.posto;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER trg_checkPostoPrenotazione
    BEFORE INSERT ON Prenotazione
    FOR EACH ROW
EXECUTE FUNCTION checkPostoPrenotazione();

--Controllo univocita login.

CREATE OR REPLACE FUNCTION checkLoginUnivoco()
RETURNS TRIGGER AS $$
DECLARE
    numero INTEGER;
BEGIN
    IF EXISTS (
        SELECT 1
        FROM Cliente
        WHERE login = new.login
        UNION ALL
        SELECT 1
        FROM Hostess
        WHERE login = new.login
        UNION ALL
        SELECT 1
        FROM Pilota
        WHERE login = new.login
    ) THEN
        RAISE EXCEPTION 'Login % gia'' esistente', new.login;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER trg_cliente_login
  BEFORE INSERT OR UPDATE ON Cliente FOR EACH ROW
  EXECUTE FUNCTION checkLoginUnivoco();

CREATE OR REPLACE TRIGGER trg_pilota_login
  BEFORE INSERT OR UPDATE ON Pilota FOR EACH ROW
  EXECUTE FUNCTION checkLoginUnivoco();

CREATE OR REPLACE TRIGGER trg_hostess_login
  BEFORE INSERT OR UPDATE ON Hostess FOR EACH ROW
  EXECUTE FUNCTION checkLoginUnivoco();