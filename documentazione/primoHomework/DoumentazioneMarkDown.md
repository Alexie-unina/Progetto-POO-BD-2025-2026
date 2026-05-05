# Documentazione 
### Il dominio di interesse che abbiamo scelto è quello del sistema informativo di un aeroporto, che considera le entità cliente, hostess, e pilota (ereditanti dall’entità Utente), volo, aereo, e posto. Lo scopo è quello di gestire le prenotazioni eseguite dai clienti e assegnarle al determinato posto di un determinato volo. Ad ogni volo sono assegnati due piloti (pilota e co-pilota) e due hostess, inoltre ai clienti è possibile scegliere 4 classi diverse per quanto riguarda il posto.


` (ECONOMY, ECONOMYPLUS, BUSINESS, PRIMA) `

* le entità usate sono Utente, pilota, cliente, hostess, volo, aereo, posto.

* Utente è specializzata in pilota, cliente, o hostess.

* Un cliente è la specializzazione di un Utente, come lo è un pilota o un’hostess.

>Un cliente è in relazione con volo tramite la classe prenotazione, dove un cliente prenota un qualsiasi numero di voli e un volo è prenotato da un qualsiasi numero di clienti.

> Un pilota gestisce un qualsiasi numero di voli, ed un’hostess serve su un qualsiasi numero di voli, mentre un singolo volo è gestito da precisamente due piloti e su di esso servono precisamente due hostess.

> Un volo può avvenire su un singolo aereo, e un singolo aereo può ospitare un qualsiasi numero di voli.

> L’entità astratta Utente dispone degli attributi login, password, nomeCompleto, codiceFiscale e numeroDiCellulare, tutti di tipo String, e dispone degli appropriati metodi di setter (escludendo login che funge da chiave primaria) e getter (escludendo password).

> La classe Cliente dispone dell’attributo idCliente di tipo String, il suo getter e il metodo prenotaVolo, che crea una prenotazione per un determinato volo in un determinato posto, e una determinata classe.

 >La classe Pilota dispone degli attributi idPilota di tipo String e Salario di tipo double, e degli appropriati getter e setter.

>La classe Hostess dispone degli attributi idHostess di tipo String e Salario di tipo double, e degli appropriati getter e setter.

>La classe Volo dispone degli attributi idVolo di tipo String, destinazione di tipo String, durata di tipo int (espressa in minuti), piloti che è un’Array[2] di Pilota e hostess che è un’Array[2] di Hostess, oltre che appropriati getter e setter.

>La classe prenotazione dispone degli attributi idPrenotazione di tipo String, un Cliente ed un Volo, e posto di tipo String, e una classe di tipo enumerativo oltre che appropriati getter e setter.

>La classe Aereo dispone degli attributi idAereo di tipo String, Modello di tipo String e nPosti di tipo int, oltre che appropriati getter e setter.
>
![[Agenzia.png]]