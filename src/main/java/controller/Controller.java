package controller;

import dao.*;
import exceptions.ChiaveException;
import exceptions.ParameterMissingException;
import model.*;

import javax.naming.AuthenticationException;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  Controller {

    private ArrayList<Cliente> clienti  = new ArrayList<Cliente>();
    private ArrayList<Aereo>   aerei    = new ArrayList<Aereo>();
    private ArrayList<Pilota>  piloti   = new ArrayList<Pilota>();
    private ArrayList<Hostess> hostess  = new ArrayList<Hostess>();
    private ArrayList<Volo>    voli     = new ArrayList<Volo>();
    private ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
    public void exit(){
        System.exit(0);
    }

    /*
       login non inserito
       password non inserita
       nome non inserito
       idPilota non inserito
       codiceFiscale non inserito
       salario non inserito
       codiceFiscale non valido
       numeroDiCellulare non valido
       salario non valido
       login già esistente
       idPilota già esistente
       scemo chi legge
    */

    //crea un oggetto di tipo Cliente, facendo i dovuti controlli sui suoi attributi
    public void creaCliente(String login,
                            String password,
                            String nomeCompleto,
                            String codiceFiscale,
                            String numeroDiCellulare,
                            String idCliente) throws ChiaveException, AuthenticationException, SQLException {
        boolean hasNumero = true;
        if(login.isBlank() || idCliente.isBlank()){
            throw new ChiaveException("Una o piu chiavi (login o idCliente) mancanti");
        }
        if(password.isBlank() || password.length() < 8)
            throw new AuthenticationException("password mancante o troppo corta");
        if(nomeCompleto.isBlank())
            throw new ParameterMissingException("Nome Mancante");
        if(codiceFiscale.length() != 16)
            throw new ParameterMissingException("Formato codice fiscale non corretto");
        if(numeroDiCellulare.isBlank())
            hasNumero = false;
        for (Cliente cliente : clienti){
            if(cliente.getLogin().equals(login) || cliente.getIdCliente().equals(idCliente)){
                throw new ChiaveException("Login o id Cliente gia' esistenti");
            }
        }

        ClienteDAO clienteDAO = new ClienteDAO();

        if(hasNumero){
            clienteDAO.salvaCliente(new Cliente(login,password,nomeCompleto,codiceFiscale,numeroDiCellulare,idCliente));
        }else{
            clienteDAO.salvaCliente(new Cliente(login,password,nomeCompleto,codiceFiscale,null,idCliente));
        }
    }
    //OBSOLETO
    //restituisce l'arraylist di clienti
    public ArrayList<Cliente> getClienti(){
        return clienti;
    }


    //restituisce la lista contenente array di stringhe formati dai singoli attributi di cliente
    public List<String[]> getListaClienti() throws SQLException {
        List<String[]> listaClienti = new ArrayList<>();
        ClienteDAO clienteDAO = new ClienteDAO();
        for (Cliente cliente : clienteDAO.getClienti()){
            String[] c = new String[6];
            c[0] = cliente.getLogin();
            c[1] = cliente.getPassword();
            c[2] = cliente.getNomeCompleto();
            c[3] = cliente.getCodiceFiscale();
            c[4] = cliente.getNumeroDiCellulare();
            c[5] = cliente.getIdCliente();

            listaClienti.add(c);
        }

        clienteDAO.closeConnection();
        return listaClienti;
    }

    //stampa su terminale login e nomecompleto di ciascun cliente
    public void stampaClienti(){ //Per debug
        for (Cliente cliente : clienti){
            System.out.println(cliente.getLogin() + cliente.getNomeCompleto());
        }
    }

    //OBSOLETO
    //restituisce un array di stringhe dove ogni elemento è un attributo di cliente sotto forma di stringa
    public String[] getCliente(int i){
        String[] cliente = new String[5];
        Cliente c = clienti.get(i);
        cliente[0] = c.getLogin();
        cliente[1] = c.getNomeCompleto();
        cliente[2] = c.getCodiceFiscale();
        cliente[3] = c.getNumeroDiCellulare();
        cliente[4] = c.getIdCliente();
        return cliente;
    }

    //rimuove un cliente dalla lista clienti
    public void rimuoviCliente(String idCliente)throws Exception{
        if(idCliente.isEmpty())
            throw new Exception("nessun cliente selezionato");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.rimuoviCliente(idCliente);
        clienteDAO.closeConnection();
    }

    //crea un oggetto di tipo Pilota, facendo i dovuti controlli sui suoi attributi
    public void creaPilota(String login,
                            String password,
                            String nomeCompleto,
                            String codiceFiscale,
                            String numeroDiCellulare,
                            String idPilota,
                            String salario) throws ChiaveException, AuthenticationException, SQLException {
        int salarioInt;

        try {
            salarioInt = Integer.parseInt(salario);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Il salario deve essere un numero");
        }
        boolean hasNumero = true;
        if(login.isBlank() || idPilota.isBlank()){
            throw new ChiaveException("Una o piu chiavi (login o idPilota) mancanti");
        }
        if(password.isBlank() || password.length() < 8)
            throw new AuthenticationException("password mancante o troppo corta");
        if(nomeCompleto.isBlank())
            throw new ParameterMissingException("Nome Mancante");
        if(codiceFiscale.length() != 16)
            throw new ParameterMissingException("Formato codice fiscale non corretto");
        if(numeroDiCellulare.isBlank())
            hasNumero = false;
        if(salarioInt < 0)
            throw new IllegalArgumentException("salario non valido");
        for (Pilota pilota : piloti){
            if(pilota.getLogin().equals(login) || pilota.getIdPilota().equals(idPilota)){
                throw new ChiaveException("Login o idPilota gia' esistenti");
            }
        }
        PilotaDAO pilotaDAO = new PilotaDAO();

        if(hasNumero){
            pilotaDAO.salvaPilota(new Pilota(login,password,nomeCompleto,codiceFiscale,numeroDiCellulare,idPilota,salarioInt));
        }else{
            pilotaDAO.salvaPilota(new Pilota(login,password,nomeCompleto,codiceFiscale,null,idPilota, salarioInt));
        }

    }
    //restituisce l'arraylist dei piloti
    public ArrayList<Pilota> getPiloti() {
        return piloti;
    }

    //restituisce un arraylist contenente array di stringhe formati dai singoli attributi di pilota
    public List<String[]> getListaPiloti() throws SQLException {
        List<String[]> listaPiloti = new ArrayList<>();
        PilotaDAO pilotaDAO = new PilotaDAO();
        for (Pilota pilota : pilotaDAO.getListaPiloti()){
            String[] p = new String[7];
            p[0] = pilota.getLogin();
            p[1] = pilota.getPassword();
            p[2] = pilota.getNomeCompleto();
            p[3] = pilota.getCodiceFiscale();
            p[4] = pilota.getNumeroDiCellulare();
            p[5] = pilota.getIdPilota();
            p[6] = String.valueOf(pilota.getSalario());

            listaPiloti.add(p);
        }

        pilotaDAO.closeConnection();
        return listaPiloti;
    }

    //stampa su terminale login e nome di ciascun pilota
    public void stampaPiloti(){ //Per debug
        for (Pilota pilota : piloti){
            System.out.println(pilota.getLogin() + pilota.getNomeCompleto());
        }
    }

    //OBSOLETO
    //restituisce un array di stringhe dove ogni elemento è un attributo di pilota sotto forma di stringa
    public String[] getPilota(int i){
        String[] pilota = new String[6];
        Pilota p = piloti.get(i);
        pilota[0] = p.getLogin();
        pilota[1] = p.getNomeCompleto();
        pilota[2] = p.getCodiceFiscale();
        pilota[3] = p.getNumeroDiCellulare();
        pilota[4] = p.getIdPilota();
        pilota[5] = String.valueOf(p.getSalario());
        return pilota;
    }

    //rimuove un pilota dalla lista dei piloti
    public void rimuoviPilota(String idPilota)throws Exception{
        if(idPilota.isEmpty()){
            throw new Exception("nessun pilota selezionato");
        }
        PilotaDAO pilotaDAO = new PilotaDAO();
        pilotaDAO.rimuoviPilota(idPilota);
        pilotaDAO.closeConnection();
    }

    //crea un oggetto di tipo Hostess, facendo i dovuti controlli sugli attributi
    public void creaHostess(String login,
                           String password,
                           String nomeCompleto,
                           String codiceFiscale,
                           String numeroDiCellulare,
                           String idHostess,
                           String salario) throws ChiaveException, AuthenticationException, SQLException {
        int salarioInt;

        try {
            salarioInt = Integer.parseInt(salario);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Il salario deve essere un numero");
        }
        boolean hasNumero = true;
        if(login.isBlank() || idHostess.isBlank()){
            throw new ChiaveException("Una o piu chiavi (login o idHostess) mancanti");
        }
        if(password.isBlank() || password.length() < 8)
            throw new AuthenticationException("password mancante o troppo corta");
        if(nomeCompleto.isBlank())
            throw new ParameterMissingException("Nome Mancante");
        if(codiceFiscale.length() != 16)
            throw new ParameterMissingException("Formato codice fiscale non corretto");
        if(numeroDiCellulare.isBlank())
            hasNumero = false;
        if(salarioInt < 0)
            throw new IllegalArgumentException("salario non valido");
        for (Hostess hostess : hostess){
            if(hostess.getLogin().equals(login) || hostess.getIdHostess().equals(idHostess)){
                throw new ChiaveException("Login o id Hostess gia' esistenti");
            }
        }
        HostessDAO hostessDAO = new HostessDAO();

        if(hasNumero){
            hostessDAO.salvaHostess(new Hostess(login,password,nomeCompleto,codiceFiscale,numeroDiCellulare,idHostess,salarioInt));
        }else{
            hostessDAO.salvaHostess(new Hostess(login,password,nomeCompleto,codiceFiscale,null,idHostess, salarioInt));
        }
    }

    //OBSOLETO
    //restituisce l'arraylist degli hostess
    public ArrayList<Hostess> getHostesses(){
        return hostess;
    }

    //OBSOLETO
    //restituisce un array di stringhe dove ogni elemento è un attributo di hostess sotto forma di stringa
    public String[] getHostess(int i){
        String[] hostess = new String[6];
        Hostess h = this.hostess.get(i);
        hostess[0] = h.getLogin();
        hostess[1] = h.getNomeCompleto();
        hostess[2] = h.getCodiceFiscale();
        hostess[3] = h.getNumeroDiCellulare();
        hostess[4] = h.getIdHostess();
        hostess[5] = String.valueOf(h.getSalario());
        return hostess;
    }

    //restituisce una lista di array di stringhe contenenti i singoli attributi di hostess
    public List<String[]> getListaHostess() throws SQLException {
        List<String[]> listaHostess = new ArrayList<>();
        HostessDAO hostessDAO = new HostessDAO();
        for (Hostess hostess : hostessDAO.getListaHostess()){
            String[] h = new String[7];
            h[0] = hostess.getLogin();
            h[1] = hostess.getPassword();
            h[2] = hostess.getNomeCompleto();
            h[3] = hostess.getCodiceFiscale();
            h[4] = hostess.getNumeroDiCellulare();
            h[5] = hostess.getIdHostess();
            h[6] = String.valueOf(hostess.getSalario());

            listaHostess.add(h);
        }

        hostessDAO.closeConnection();
        return listaHostess;
    }

    //stampa su terminale login e nome di ciascun hostess
    public void stampaHostess(){ //Per debug
        for (Hostess hostess : hostess){
            System.out.println(hostess.getLogin() + hostess.getNomeCompleto());
        }
    }

    //rimuove un hostess dalla lista degli hostess
    public void rimuoviHostess(String idHostess)throws Exception{
        if (idHostess.isBlank()){
            throw new Exception("nessun hostess selezionato");
        }
        HostessDAO hostessDAO = new HostessDAO();
        hostessDAO.rimuoviHostess(idHostess);
        hostessDAO.closeConnection();
    }

    /*
    public void creaAereo(String idAereo,
                          String modello,
                          String nPosti) throws IllegalArgumentException {
        int nPostiInt;

        try {
            nPostiInt = Integer.parseInt(nPosti);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Il numero di posti deve essere un numero");
        }

        if (idAereo.isBlank())
            throw new InvalidParameterException("Il campo idaereo è vuoto");
        if (modello.isBlank())
            throw new InvalidParameterException("il campo modello è vuoto");
        if (nPostiInt < 0)
            throw new InvalidParameterException(("numero posti non valido"));

        for (Aereo aereo : aerei) {
            if (aereo.getIdAereo().equals(idAereo))
                throw new IllegalArgumentException("Id Aereo già in uso");
        }

        aerei.add(new Aereo(idAereo, modello, nPostiInt));
    }
*/
    //crea un oggetto di tipo Aereo, facendo i dovuti controlli sugli attributi
    public void creaAereo(String idAereo, String modello, String nPosti) throws SQLException {
        int nPostiInt;

        try {
            nPostiInt = Integer.parseInt(nPosti);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Il numero di posti deve essere un numero");
        }

        if (idAereo.isBlank())
            throw new InvalidParameterException("Il campo idaereo è vuoto");
        if (modello.isBlank())
            throw new InvalidParameterException("il campo modello è vuoto");
        if (nPostiInt < 0)
            throw new InvalidParameterException(("numero posti non valido"));

        var aereoDAO = new AereoDAO();
        aereoDAO.salvaAereo(new Aereo(idAereo, modello, nPostiInt));
        aereoDAO.closeConnection();
        return;
    }
    //restituisce un arraylist di array di stringhe contenente id modello e nPosti di ciascun aereo
    public List<String[]> getListaAerei() throws SQLException {
        List<String[]> aerei = new ArrayList<>();
        AereoDAO aereoDAO = new AereoDAO();
        for(Aereo a : aereoDAO.getListaAerei()){
            String[] aereo = new String[3];
            aereo[0] = a.getIdAereo();
            aereo[1] = a.getModello();
            aereo[2] = String.valueOf(a.getnPosti());
            aerei.add(aereo);
        }
        aereoDAO.closeConnection();
        return aerei;
    }

    //OBSOLETO
    //restituisce una stringa contenente gli attributi di un singolo aereo
    public ArrayList<String> getProprietaAereo(int i){
        ArrayList<String> proprietaAereo = new ArrayList<>();
        Aereo a = aerei.get(i);
        proprietaAereo.add(a.getIdAereo());
        proprietaAereo.add(a.getModello());
        proprietaAereo.add(Integer.toString(a.getnPosti()));
        return proprietaAereo;
    }

    //restituisce un array di stringhe contenente gli attributi di un aereo sotto forma di stringa
//    public String[] getAereo(int i)throws Exception{
//        if(i == -1) //-1 è il valore restituito dal metodo getSelectedIndex di TextArea in caso nessun elemento sia selezionato
//            throw new Exception("aereo non selezionato");
//        Aereo a = aerei.get(i);
//        String[] aereo = new String[3];
//        aereo[0] = a.getIdAereo();
//        aereo[1] = a.getModello();
//        aereo[2] = String.valueOf(a.getnPosti());
//        return aereo;
//    }

    public String[] getAereo(String idAereo) throws SQLException {
        Aereo a = new AereoDAO().getAereo(idAereo);
        String[] aereo = new String[3];
        aereo[0] = a.getIdAereo();
        aereo[1] = a.getModello();
        aereo[2] = String.valueOf(a.getnPosti());
        return aereo;
    }

    //rimuove un aereo dalla lista degli aerei
    public void rimuoviAereo(String idAereo)throws Exception{
        if(idAereo.isEmpty()) //-1 è il valore restituito dal metodo getSelectedIndex di TextArea in caso nessun elemento sia selezionato
            throw new Exception("nessun aereo selezionato");
        AereoDAO aereoDAO = new AereoDAO();
        aereoDAO.rimuoviAereo(idAereo);
        aereoDAO.closeConnection();
    }

    //stampa "dbg" su terminale
    public void dbg(){ //funzione debug
        System.out.println("dbg");
    }

    //crea un oggetto di tipo Volo, facendo i dovuti controlli sugli attributi
    public void creaVolo(String idVolo,
                         String destinazione,
                         String durata,
                         String idPilota,
                         String idCoPilota,
                         String idHostess1,
                         String idHostess2,
                         String idAereo) throws ChiaveException, AuthenticationException, SQLException {
        int durataInt;
        try {
            durataInt = Integer.parseInt(durata);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("la durata deve essere un numero");
        }
        if(idVolo.isBlank())
            throw new InvalidParameterException("Il campo idVolo è vuoto");
        if(destinazione.isBlank())
            throw new InvalidParameterException("Il campo destinazione è vuoto");
        if(durata.isBlank())
            throw new InvalidParameterException("Il campo durata è vuoto");
        if(idPilota.isEmpty())
            throw new InvalidParameterException("manca il pilota");
        if(idCoPilota.isEmpty())
            throw new InvalidParameterException("manca il copilota");
        if(idHostess1.isEmpty())
            throw new InvalidParameterException("manca la prima hostess");
        if(idHostess2.isEmpty())
            throw new InvalidParameterException("manca la seconda hostess");
        if(idPilota.equals(idCoPilota))
            throw new ChiaveException("inserire due piloti distinti");
        if(idHostess1.equals(idHostess2))
            throw new ChiaveException("inserire due hostess distinti");
        if(idAereo.isEmpty())
            throw new InvalidParameterException("manca l'aereo");
        VoloDAO voloDAO = new VoloDAO();
        voloDAO.salvaVolo(new Volo(idVolo,
                destinazione,
                durataInt,
                new PilotaDAO().getPilota(idPilota),
                new PilotaDAO().getPilota(idCoPilota),
                new HostessDAO().getHostess(idHostess1),
                new HostessDAO().getHostess(idHostess2),
                new AereoDAO().getAereo(idAereo)));
        voloDAO.closeConnection();
    }

    //restituisce un arraylist di array di stringhe contenenti i dati di ciascun volo
    public List<String[]> getListaVoli() throws SQLException {
        VoloDAO voloDAO = new VoloDAO();
        List<String[]> listaVoli = new ArrayList<>();
        for (Volo volo : voloDAO.getListaVoli()){
            String[] v = new String[9];
            v[0] = volo.getIdVolo();
            v[1] = volo.getDestinazione();
            v[3] = String.valueOf(volo.getDurata());
            v[4] = volo.getPilota().getIdPilota()    + " " + volo.getPilota().getNomeCompleto();
            v[5] = volo.getCoPilota().getIdPilota()  + " " + volo.getCoPilota().getNomeCompleto();
            v[6] = volo.getHostess1().getIdHostess() + " " + volo.getHostess1().getNomeCompleto();
            v[7] = volo.getHostess2().getIdHostess() + " " + volo.getHostess2().getNomeCompleto();
            v[8] = volo.getAereo().getIdAereo();

            listaVoli.add(v);
        }
        voloDAO.closeConnection();
        return listaVoli;
    }

    //restituisce un array di stringhe contenente gli attributi di volo sotto forma di stringhe
    public String[] getVolo(int i) {
        String[] volo = new String[8];
        Volo v = voli.get(i);
        volo[0] = v.getIdVolo();
        volo[1] = v.getDestinazione();
        volo[2] = String.valueOf(v.getDurata());
        volo[3] = v.getPilota().getIdPilota() + " " + v.getPilota().getNomeCompleto();
        volo[4] = v.getCoPilota().getIdPilota() + " " + v.getCoPilota().getNomeCompleto();
        volo[5] = v.getHostess1().getIdHostess() + " " + v.getHostess1().getNomeCompleto();
        volo[6] = v.getHostess2().getIdHostess() + " " + v.getHostess2().getNomeCompleto();
        volo[7] = v.getAereo().getIdAereo() + " " + v.getAereo().getModello();
        return volo;
    }

    //rimuove un volo dalla lista dei voli
    public void rimuoviVolo(String idVolo)throws Exception{
        if(idVolo.isEmpty()) //-1 è il valore restituito dal metodo getSelectedIndex di TextArea in caso nessun elemento sia selezionato
            throw new Exception("nessun volo selezionato");
        VoloDAO voloDAO = new VoloDAO();
        voloDAO.rimuoviVolo(idVolo);
        voloDAO.closeConnection();
    }

    //crea un oggetto di tipo Prenotazione, facendo i dovuti controlli sugli attributi
    public void creaPrenotazione(String idPrenotazione,
                                 String idCliente,
                                 String idVolo,
                                 String posto,
                                 String classe) throws ChiaveException, AuthenticationException, InvalidParameterException, SQLException {
            if(idPrenotazione.isBlank())
                throw new InvalidParameterException("idPrenotazione non inserito");
            if(idCliente.isEmpty())
                throw new InvalidParameterException("cliente non inserito");
            if(idVolo.isEmpty() )
                throw new InvalidParameterException("volo non inserito");
            if(posto.isBlank())
                throw new InvalidParameterException("posto non inserito");
            if(classe.isBlank())
                throw new InvalidParameterException("classe non inserita");
//            switch (classe){
//                case "Economy":
//                    classe = "ECONOMY";
//                    break;
//                case "Economy Plus":
//                    classe = "ECONOMYPLUS";
//                    break;
//                case "Business":
//                    classe = "BUSINESS";
//                    break;
//                case "Prima Classe":
//                    classe = "PRIMA";
//                    break;
//            }
            //TODO
            //OVERBOOKING LIVELLO DATABASE
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            prenotazioneDAO.salvaPrenotazione(new Prenotazione(idPrenotazione, new ClienteDAO().getCliente(idCliente), new VoloDAO().getVolo(idVolo), posto, classe));
    }

    //restituisce un arraylist di stringhe contenente id e classe di ciascuna prenotazione
    public ArrayList<String[]> getListaPrenotazioni() throws SQLException {
        ArrayList<String[]> listaPrenotazioni = new ArrayList<>();
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        for(Prenotazione prenotazione : prenotazioneDAO.getListaPrenotazioni()){
            String[] p = new  String[9];
            p[0] = prenotazione.getIdPrenotazione();
            p[1] = prenotazione.getCliente().getIdCliente();
            p[2] = prenotazione.getCliente().getNomeCompleto();
            p[3] = prenotazione.getVolo().getIdVolo();
            p[4] = prenotazione.getVolo().getDestinazione();
            p[5] = prenotazione.getVolo().getPilota().getIdPilota();
            p[6] = prenotazione.getVolo().getCoPilota().getNomeCompleto();
            p[7] = prenotazione.getPosto();
            p[8] = prenotazione.getClasse();

            listaPrenotazioni.add(p);
        }
        prenotazioneDAO.closeConnection();
        return listaPrenotazioni;
    }

    //restituisce un array di stringhe contenente ciascun attributo di una prenotazione sotto forma di stringhe
    public String[] getPrenotazione(int i){
        String[] prenotazione = new String[5];
        Prenotazione p = prenotazioni.get(i);
        prenotazione[0] = p.getIdPrenotazione();
        prenotazione[1] = p.getCliente().getIdCliente() + " " + p.getCliente().getNomeCompleto();
        prenotazione[2] = p.getVolo().getIdVolo() + " " + p.getVolo().getDestinazione();
        prenotazione[3] = p.getPosto();
        prenotazione[4] = p.getClasse();
        return prenotazione;
    }

    //rimuove una prenotazione dalla lista delle prenotazioni
    public void rimuoviPrenotazione(String idPrenotazione)throws Exception{
        if(idPrenotazione.isEmpty()) //-1 è il valore restituito dal metodo getSelectedIndex di TextArea in caso nessun elemento sia selezionato
            throw new Exception("nessuna prenotazione selezionato");
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        prenotazioneDAO.rimuoviPrenotazione(idPrenotazione);
        prenotazioneDAO.closeConnection();
    }
}

