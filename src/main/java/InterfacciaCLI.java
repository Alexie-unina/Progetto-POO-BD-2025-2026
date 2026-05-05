import model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfacciaCLI {
//    Pilota c = new Pilota("EmyServi", "password1", "Emy Servillo", "SRVMLR06R08A024O",
//            "+393917021117", "P934", 6767.67);
//    Pilota c1 = new Pilota("alekg44", "password2", "Alessio Riccio",
//            "ALKGGG44R04A023F", "+393917021112", "P134", 5000.00);
//    Hostess h1 = new Hostess("AlexPizzi", "passwordgay", "Alessandro Pizzi", "ALALALALLLLALLAAL",
//            "+393918691020", "H563", 1324.3);
//    Hostess h2 = new Hostess("ErDeltarune", "Monster", "Emanuele toledo", "DDG315DKLG51A95V",
//            "+393849583940", "H654", 32142.3);
//    Aereo A = new Aereo("778834", "Piselling", 9000);
//    Cliente C = new Cliente("Alessandro", "pass", "Alessandro qualcosa", "AJDLGJ395LF",
//            "C3533322");
//    Volo v = new Volo("V43452", "MonteRuscello", 176, c, c1, h1, h2, A);
//    Prenotazione p = new Prenotazione("P1512", C, v, "14F", "ECONOMY");

    private ArrayList<Pilota> piloti = new ArrayList<Pilota>();
    private ArrayList<Hostess> hostess = new ArrayList<Hostess>();
    private ArrayList<Cliente> clienti = new ArrayList<Cliente>();
    private ArrayList<Volo> voli = new ArrayList<Volo>();
    private ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
    private ArrayList<Aereo> aerei = new ArrayList<Aereo>();


    public void addAereo(String idAereo, String modello, int nPosti){
        for(Aereo aereoI : aerei)
            if(aereoI.getIdAereo().equals(idAereo))
                throw new IllegalArgumentException("Questo Id Esiste già");
        aerei.add(new Aereo(idAereo,modello,nPosti));
    }
    public void addPilota(String login, String password, String nomeCompleto, String codiceFiscale, String idPilota, double salario) {
        for(Pilota p : piloti)
            if(p.getLogin().equals(login) || p.getIdPilota().equals(idPilota))
                throw new IllegalArgumentException("Questo Id Esiste già");
        piloti.add(new Pilota(login, password, nomeCompleto, codiceFiscale, idPilota, salario));
    }

    public void addHostess(String login, String password, String nomeCompleto, String codiceFiscale, String idHostess, double salario) {
        for(Hostess h : hostess)
            if(h.getLogin().equals(login))
                throw new IllegalArgumentException("Questo Id Esiste già");
        hostess.add(new Hostess(login, password, nomeCompleto, codiceFiscale, idHostess, salario));
    }

    public void addCliente(String login, String password, String nomeCompleto, String codiceFiscale, String idCliente) {
        for(Cliente c : clienti)
            if(c.getIdCliente().equals(idCliente))
                throw new IllegalArgumentException("Questo Id Esiste già");
        clienti.add(new Cliente(login, password, nomeCompleto, codiceFiscale, idCliente));
    }

    public void addVolo(String idVolo,
                        String destinazione,
                        int durata,
                        Pilota pilota,
                        Pilota copilota,
                        Hostess hostess1,
                        Hostess hostess2,
                        Aereo aereo) {
        for(Volo volo : voli)
            if(volo.getIdVolo().equals(idVolo))
                throw new IllegalArgumentException("Questo Id Esiste già");
        voli.add(new Volo(idVolo, destinazione, durata, pilota, copilota, hostess1, hostess2, aereo));
    }

    public void addPrenotazione(String idPrenotazione, Cliente cliente, Volo volo, String posto, String classe) {
        for(Prenotazione p : prenotazioni)
            if(p.getIdPrenotazione().equals(idPrenotazione))
                throw new IllegalArgumentException("Questo Id Esiste già");
        prenotazioni.add(new Prenotazione(idPrenotazione, cliente, volo, posto, classe));
    }

    Scanner sc = new Scanner(System.in);

    public void premiPerContinuare() {
        System.out.println("premere un bottone per continuare...");
        sc.nextLine();
        sc.nextLine();
    }

    public void interfacciaCli() throws InputMismatchException {
        boolean exit = false;
        int input;

        //================
        String login, password, nomeCompleto, codiceFiscale;
        String idPilota, idCliente, idVolo, idPrenotazione, idHostess,destinazione;
        double salario;
        Pilota p1,p2;
        Hostess h1,h2;
        String temp;
        int durata;
        Aereo a1;
        Cliente c;
        Volo v;
        String posto,classe;
        String idAereo,modello;
        int nPosti;

        while (!exit) {
            System.out.println("Scegliere un Opzione");
            System.out.println("1. Aggiungi un nuovo pilota");
            System.out.println("2. Aggiungi un nuovo hostess");
            System.out.println("3. Aggiungi un nuovo cliente");
            System.out.println("4. Aggiungi un nuovo volo");
            System.out.println("5. Aggiungi un nuovo aereo");
            System.out.println("6. Aggiungi una nuova prenotazione");
            System.out.println("7. Stampa Lista piloti");
            System.out.println("8. Stampa Lista hostess");
            System.out.println("9. Stampa Lista clienti");
            System.out.println("10. Stampa Lista voli");
            System.out.println("11. Stampa Lista aerei");
            System.out.println("12. Stampa Lista prenotazioni");
            System.out.println("0. Uscire");
            System.out.print("Input: ");
            input = sc.nextInt();
            switch (input) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    //Crea Pilota
                    sc.nextLine();
                    System.out.println("Inserire login Pilota:");
                    login = sc.nextLine();
                    System.out.println("Inserire password Pilota:");
                    password = sc.nextLine();
                    System.out.println("Inserire nome Pilota:");
                    nomeCompleto = sc.nextLine();
                    System.out.println("Inserire codice fiscale Pilota:");
                    codiceFiscale = sc.nextLine();
                    System.out.println("Inserire ID pilota:");
                    idPilota = sc.nextLine();
                    System.out.println("Inserire salario Pilota:");
                    salario = sc.nextDouble();
                    addPilota(login, password, nomeCompleto, codiceFiscale, idPilota, salario);
                    break;
                case 2:
                    //Crea Hostess
                    sc.nextLine();
                    System.out.println("Inserire login Hostess:");
                    login = sc.nextLine();
                    System.out.println("Inserire password Hostess:");
                    password = sc.nextLine();
                    System.out.println("Inserire nome Hostess:");
                    nomeCompleto = sc.nextLine();
                    System.out.println("Inserire codice fiscale Hostess:");
                    codiceFiscale = sc.nextLine();
                    System.out.println("Inserire ID Hostess:");
                    idHostess = sc.nextLine();
                    System.out.println("Inserire salario Hostess:");
                    salario = sc.nextDouble();
                    addHostess(login, password, nomeCompleto, codiceFiscale, idHostess, salario);
                    break;
                case 3:
                    //Crea Cliente
                    sc.nextLine();
                    System.out.println("Inserire login Cliente:");
                    login = sc.nextLine();
                    System.out.println("Inserire password Cliente:");
                    password = sc.nextLine();
                    System.out.println("Inserire nome Cliente:");
                    nomeCompleto = sc.nextLine();
                    System.out.println("Inserire codice fiscale Cliente:");
                    codiceFiscale = sc.nextLine();
                    System.out.println("Inserire ID Cliente:");
                    idCliente = sc.nextLine();
                    addCliente(login, password, nomeCompleto, codiceFiscale, idCliente);
                    break;
                case 4:
                    //Crea Volo
                    sc.nextLine();
                    System.out.println("Inserire idVolo:");
                    idVolo = sc.nextLine();
                    System.out.println("Inserire Destinazione:");
                    destinazione = sc.nextLine();
                    System.out.println("Inserire Durata (minuti):");
                    durata = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Inserire IDPilota:");
                    temp = sc.nextLine();
                    p1 = null;
                    for(Pilota p : piloti){
                        if(p.getIdPilota().equals(temp)){
                            p1 = p;
                        }
                    }
                    if(p1 == null)
                        throw new InputMismatchException("Pilota non trovato");


                    System.out.println("Inserire IDCopilota:");
                    temp = sc.nextLine();
                    p2 = null;
                    for(Pilota p : piloti){
                        if(p.getIdPilota().equals(temp)){
                            p2 = p;
                        }
                    }
                    if(p2 == null)
                        throw new InputMismatchException("CoPilota non trovato");

                    System.out.println("Inserire IDHostess 1:");
                    temp = sc.nextLine();
                    h1 = null;
                    for(Hostess h : hostess){
                        if(h.getIdHostess().equals(temp)){
                            h1 = h;
                        }
                    }
                    if(h1 == null)
                        throw new InputMismatchException("Hostess non trovata");

                    System.out.println("Inserire IDHostess 2:");
                    temp = sc.nextLine();
                    h2 = null;
                    for(Hostess h : hostess){
                        if(h.getIdHostess().equals(temp)){
                            h2 = h;
                        }
                    }
                    if(h2 == null)
                        throw new InputMismatchException("Hostess non trovata");

                    if(h1 == h2 || p1 == p2)
                        throw new RuntimeException("EEEEEEEEEEHHH BIRBANTELLO!!!");


                    System.out.println("Inserire IDAereo:");
                    temp = sc.nextLine();
                    a1 = null;
                    for (Aereo a : aerei){
                        if(a.getIdAereo().equals(temp)){
                            a1 = a;
                        }
                    }
                    if (a1 == null){
                        throw new InputMismatchException("Aereo non trovato");
                    }

                    addVolo(idVolo,destinazione,durata,p1,p2,h1,h2,a1);
                    System.out.println("Aggiunto volo");
                    premiPerContinuare();
                    break;
                case 5:
                    //AddAereo
                    sc.nextLine();
                    System.out.println("Inserire IdAereo");
                    idAereo = sc.nextLine();
                    System.out.println("Inserire modello Aereo");
                    modello = sc.nextLine();
                    System.out.println("Inserire numero posti");
                    nPosti = sc.nextInt();
                    sc.nextLine();
                    addAereo(idAereo,modello,nPosti);
                    System.out.println("Aggiunto aereo!");
                    sc.nextLine();
                    premiPerContinuare();
                    break;
                case 6:
                    //Crea Prenotazione
                    sc.nextLine();
                    System.out.println("Inserire IdPrenotazione");
                    idPrenotazione = sc.nextLine();
                    System.out.println("Inserire IdCliente");
                    temp = sc.nextLine();
                    c = null;
                    for(Cliente cli : clienti){
                        if(cli.getIdCliente().equals(temp))
                            c = cli;
                    }
                    if (c==null)
                        throw new InputMismatchException("Cliente non trovato");
                    System.out.println("Inserire idVolo");
                    temp = sc.nextLine();
                    v = null;
                    for(Volo vol : voli){
                        if(vol.getIdVolo().equals(temp))
                            v = vol;
                    }
                    if(v == null)
                        throw new InputMismatchException("Volo non trovato");
                    System.out.println("Inserire Posto");
                    posto = sc.nextLine();
                    System.out.println("Inserire classe (Opzioni: Economy, EconomyPlus, Business, Prima)");
                    classe = sc.nextLine();
                    addPrenotazione(idPrenotazione,c,v,posto,classe);
                    System.out.println("Aggiunta prenotazione!");
                    premiPerContinuare();
                    break;
                case 7:
                    //Get Piloti
                    for (Pilota p : piloti) {
                        System.out.println("Pilota:" + p.getIdPilota() + "\t" + p.getNomeCompleto());
                    }
                    premiPerContinuare();
                    break;
                case 8:
                    //Get Hostesses
                    for (Hostess h : hostess) {
                        System.out.println("Hostess:" + h.getIdHostess() + "\t" + h.getNomeCompleto());
                    }
                    sc.nextLine();
                    premiPerContinuare();
                    break;
                case 9:
                    //Get Clienti
                    for (Cliente cli : clienti) {
                        System.out.println("Cliente:" + cli.getIdCliente() + "\t" + cli.getNomeCompleto());
                    }
                    sc.nextLine();
                    premiPerContinuare();

                    break;
                case 10:
                    //Get Voli
                    for(Volo vol : voli){
                        System.out.println("Volo: " + vol.getIdVolo() + "\t" + vol.getDestinazione());
                    }
                    premiPerContinuare();

                    break;
                case 11:
                    //Get aerei
                    for(Aereo a : aerei){
                        System.out.println("Aereo: " + a.getIdAereo() + "\t" + a.getModello());
                    }
                    premiPerContinuare();

                    break;
                case 12:
                    //Get Prenotazioni
                    for(Prenotazione p : prenotazioni){
                        System.out.println("Prenotazione: "+ p.getIdPrenotazione() + "\t"
                                + p.getVolo().getIdVolo() + "\t"
                                + p.getCliente().getIdCliente() + "\t"
                                + p.getPosto() + "\t"
                                + p.getClasse());
                    }
                    break;
                default:
                    System.out.println("Opzione non trovata");
                    premiPerContinuare();
            }

        }
    }
}
