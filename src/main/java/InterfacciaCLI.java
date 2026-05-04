import model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfacciaCLI{
    Pilota c = new Pilota("EmyServi", "password1", "Emy Servillo", "SRVMLR06R08A024O",
            "+393917021117", "P934", 6767.67);
    Pilota c1 = new Pilota("alekg44", "password2", "Alessio Riccio",
            "ALKGGG44R04A023F", "+393917021112", "P134", 5000.00);
    Hostess h1 = new Hostess("AlexPizzi", "passwordgay", "Alessandro Pizzi", "ALALALALLLLALLAAL",
            "+393918691020", "H563",1324.3);
    Hostess h2 = new Hostess("ErDeltarune", "Monster", "Emanuele toledo", "DDG315DKLG51A95V",
            "+393849583940", "H654",32142.3);
    Aereo A = new Aereo("778834", "Piselling", 9000);
    Cliente C = new Cliente("Alessandro", "pass", "Alessandro qualcosa", "AJDLGJ395LF",
            "C3533322");
    Volo v = new Volo("V43452", "MonteRuscello", 176, c, c1, h1, h2, A);
    Prenotazione p = new Prenotazione("P1512", C, v, "14F");

    private ArrayList<Pilota> piloti = new ArrayList<Pilota>();
    private ArrayList<Hostess> hostess = new ArrayList<Hostess>();
    private ArrayList<Cliente> clienti = new ArrayList<Cliente>();
    private ArrayList<Volo> voli = new ArrayList<Volo>();
    private ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();

    public void addPilota(String login, String password, String nomeCompleto, String codiceFiscale, String idPilota, double salario) {
        piloti.add(new Pilota(login,password,nomeCompleto,codiceFiscale,idPilota,salario));
    }
    public void addHostess(String login,String password,String nomeCompleto, String codiceFiscale, String idHostess,double salario) {
        hostess.add(new Hostess(login,password,nomeCompleto,codiceFiscale,idHostess,salario));
    }
    public void addCliente(String login,String password,String nomeCompleto, String codiceFiscale, String idCliente){
        clienti.add(new Cliente(login,password,nomeCompleto,codiceFiscale,idCliente));
    }
    public void addVolo(String idVolo,
                        String destinazione,
                        int durata,
                        Pilota pilota,
                        Pilota copilota,
                        Hostess hostess1,
                        Hostess hostess2,
                        Aereo aereo){
        voli.add(new Volo(idVolo, destinazione, durata, pilota, copilota, hostess1, hostess2, aereo));
    }
    public void addPrenotazione(String idPrenotazione, Cliente cliente,Volo volo, String posto){
        prenotazioni.add(new Prenotazione(idPrenotazione, cliente, volo, posto));
    }

    Scanner sc = new Scanner(System.in);
    public void premiPerContinuare(){
        System.out.println("premere un bottone per continuare...");
        sc.nextLine();
        sc.nextLine();
    }
    public void interfacciaCli() throws InputMismatchException {
        boolean exit = false;
        int input;

        //================
        String login, password, nomeCompleto, codiceFiscale;
        String idPilota, idCliente,idVolo,idPrenotazione,idHostess;
        double salario;

        while(!exit){
            System.out.println("Scegliere un Opzione");
            System.out.println("1. Aggiungi un nuovo pilota");
            System.out.println("2. Aggiungi un nuovo hostess");
            System.out.println("3. Aggiungi un nuovo cliente");
            System.out.println("4. Aggiungi un nuovo volo");
            System.out.println("5. Aggiungi una nuova prenotazione");
            System.out.println("6. Stampa Lista piloti");
            System.out.println("7. Stampa Lista hostess");
            System.out.println("8. Stampa Lista clienti");
            System.out.println("9. Stampa Lista voli");
            System.out.println("10. Stampa Lista prenotazioni");
            System.out.print("Input: ");
            input = sc.nextInt();
            switch (input){
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
                    addPilota(login,password,nomeCompleto,codiceFiscale,idPilota,salario);
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
                    addHostess(login,password,nomeCompleto,codiceFiscale,idHostess,salario);
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
                    addCliente(login,password,nomeCompleto,codiceFiscale,idCliente);
                    break;
                case 4:
                    //Crea Volo
                    break;
                case 5:
                    //Crea Prenotazione
                    break;
                case 6:
                    //Get Piloti
                    for(Pilota p : piloti){
                        System.out.println("Pilota:" + p.getLogin() + "\t" + p.getNomeCompleto());
                    }
                    premiPerContinuare();
                    break;
                case 7:
                    //Get Hostesses
                    for (Hostess h : hostess){
                        System.out.println("Hostess:" + h.getLogin() + "\t" + h.getNomeCompleto());
                    }
                    break;
                case 8:
                    //Get Clienti
                    for (Cliente c : clienti){
                        System.out.println("Cliente:" + c.getLogin() + "\t" + c.getNomeCompleto());
                    }
                    break;
                case 9:
                    //Get Voli
                    break;
                case 10:
                    //Get Prenotazioni
                    break;
                default:
                    System.out.println("Opzione non trovata");
                    premiPerContinuare();
            }
        }
    }
}