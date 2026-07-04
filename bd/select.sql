/*Lista Piloti*/
SELECT idPilota,nomeCompleto
FROM Pilota;

/*Lista Hostess*/
SELECT idHostess,nomeCompleto
FROM Hostess;


/*Lista Clienti*/
SELECT idCliente,nomeCompleto
FROM Cliente;

/*Lista Aerei*/
SELECT idAereo,modello
FROM Aereo;

/*Lista Voli*/
SELECT idVolo,Destinazione
FROM Volo;

/*Lista Prenotazioni*/
SELECT idPrenotazione,classe
FROM Prenotazione;



/*Lista Dati Pilota*/
SELECT login,nomeCompleto,codiceFiscale,numeroCellulare,idPilota,salario
FROM pilota
WHERE idPilota = 'p001';

/*Lista Dati Hostess*/
SELECT login,nomeCompleto,codiceFiscale,numeroCellulare,idHostess,salario
FROM Hostess
WHERE idHostess = 'h067';

/*Lista Dati Cliente*/
SELECT login,nomeCompleto,codiceFiscale,numeroCellulare,idCliente
FROM Cliente
WHERE idCliente = 'c0067';

/*Lista Dati Aereo*/
SELECT idAereo, modello, nPosti
FROM Aereo
WHERE idAereo = 'a05';

/*Lista Dati Volo*/
SELECT idVolo, destinazione, durata, idPilota, idCopilota, idHostess1, idHostess2, idAereo
FROM Volo
WHERE idVolo = 'v03921';

/*Lista Dati Prenotazione*/
SELECT idPrenotazione, posto, idCliente, idVolo, classe
FROM Prenotazione
WHERE idPrenotazione = 'pr123456';