# Intefaccia grafica
il nostro progetto consiste nel sistema gestionale di un aeroporto, 
che immagazzina i dati dei vari clienti, piloti, e hostess dell'aeroporto 
oltre agli aerei, i voli previsti e le prenotazioni effettuate.
tramite la gui inclusa nel src, chi utilizza il programma può visualizzare
le rispettive liste di: piloti; hostess; clienti; aerei; voli; prenotazioni.
Dal frame di ogni lista è possibile aggiungere un elemento, riempendo ogni
campo dell'oggetto che si sta creando. in caso un campo obbligatorio non 
venisse riempito o un campo venisse riempito in modo incompleto e/o errato
verrà lanciata un eccezione e mostrato un messaggio spiegando il relativo 
errore.


> Home apre le seguenti classi
> * ListaPiloti
> * ListaHostess
> * ListaClienti
> * ListaAerei
> * ListaVoli
> * ListaPrenotazioni

> ListaPiloti apre CreaNuovoPilota che istanzia un pilota

> ListaHostess apre CreaNuovaHostess che istanzia un'hostess

> ListaClienti apre CreaNuovoCliente che istanzia un cliente

> ListaAerei apre CreaNuovoAereo che istanzia un aereo
 
> ListaVoli apre CreaNuovoVolo che istanzia un volo 

> ListaPrenotazioni apre CreaNuovaPrenotazione che istanzia una prenotazione

![Class Diagram gui.svg](Class%20Diagram%20gui.svg)