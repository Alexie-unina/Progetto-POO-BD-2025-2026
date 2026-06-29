package controller;

import exceptions.ChiaveException;
import exceptions.ParameterMissingException;
import model.*;

import javax.naming.AuthenticationException;

import java.security.InvalidParameterException;
import java.util.ArrayList;

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
    public void creaCliente(String login,
                            String password,
                            String nomeCompleto,
                            String codiceFiscale,
                            String numeroDiCellulare,
                            String idCliente) throws ChiaveException, AuthenticationException {
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

        if(hasNumero){
            clienti.add(new Cliente(login,password,nomeCompleto,codiceFiscale,numeroDiCellulare,idCliente));
        }else{
            clienti.add(new Cliente(login,password,nomeCompleto,codiceFiscale,idCliente));
        }
    }

    public ArrayList<Cliente> getClienti(){
        return clienti;
    }
    public ArrayList<String> getListaClienti(){
        ArrayList<String> listaClienti = new ArrayList<>();
        for (Cliente cliente : clienti){
            listaClienti.add(cliente.getIdCliente() + " " + cliente.getNomeCompleto());
        }
        return listaClienti;
    }
    public void stampaClienti(){ //Per debug
        for (Cliente cliente : clienti){
            System.out.println(cliente.getLogin() + cliente.getNomeCompleto());
        }
    }
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
    public void rimuoviCliente(int i)throws Exception{
        if(i == -1)
            throw new Exception("nessun cliente selezionato");
        clienti.remove(i);
    }
    public void creaPilota(String login,
                            String password,
                            String nomeCompleto,
                            String codiceFiscale,
                            String numeroDiCellulare,
                            String idPilota,
                            String salario) throws ChiaveException, AuthenticationException {
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

        if(hasNumero){
            piloti.add(new Pilota(login,password,nomeCompleto,codiceFiscale,numeroDiCellulare,idPilota, salarioInt));
        }else{
            piloti.add(new Pilota(login,password,nomeCompleto,codiceFiscale,idPilota, salarioInt));
        }
    }
    public ArrayList<Pilota> getPiloti(){
        return piloti;
    }
    public ArrayList<String> getListaPiloti(){
        ArrayList<String> listaPiloti = new ArrayList<>();
        for (Pilota pilota : piloti){
            listaPiloti.add(pilota.getIdPilota() + " " + pilota.getNomeCompleto());
        }
        return listaPiloti;
    }
    public void stampaPiloti(){ //Per debug
        for (Pilota pilota : piloti){
            System.out.println(pilota.getLogin() + pilota.getNomeCompleto());
        }
    }
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
    public void rimuoviPilota(int i)throws Exception{
        if(i == -1)
            throw new Exception("nessun pilota selezionato");
        piloti.remove(i);
    }
    public void creaHostess(String login,
                           String password,
                           String nomeCompleto,
                           String codiceFiscale,
                           String numeroDiCellulare,
                           String idHostess,
                           String salario) throws ChiaveException, AuthenticationException {
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

        if(hasNumero){
            hostess.add(new Hostess(login,password,nomeCompleto,codiceFiscale,numeroDiCellulare,idHostess, salarioInt));
        }else{
            hostess.add(new Hostess(login,password,nomeCompleto,codiceFiscale,idHostess, salarioInt));
        }
    }

    public ArrayList<Hostess> getHostesses(){
        return hostess;
    }

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

    public ArrayList<String> getListaHostess(){
        ArrayList<String> hostessList = new ArrayList<>();
        for (Hostess hostess : hostess){
            hostessList.add(hostess.getIdHostess() + " " + hostess.getNomeCompleto());
        }
        return hostessList;
    }
    public void stampaHostess(){ //Per debug
        for (Hostess hostess : hostess){
            System.out.println(hostess.getLogin() + hostess.getNomeCompleto());
        }
    }
    public void rimuoviHostess(int i)throws Exception{
        if(i == -1)
            throw new Exception("nessun hostess selezionato");
        hostess.remove(i);
    }


    public void creaAereo(String idAereo,
                          String modello,
                          String nPosti) throws InvalidParameterException,NumberFormatException,IllegalArgumentException {
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

    public ArrayList<String> getListaAerei(){
        ArrayList<String> listaAerei = new ArrayList<>();
        for (Aereo aereo : aerei){
            listaAerei.add(aereo.getIdAereo() +  " " + aereo.getModello());
        }
        return listaAerei;
    }

    public ArrayList<String> getProprietaAereo(int indice){
        ArrayList<String> proprietaAereo = new ArrayList<>();
        Aereo a = aerei.get(indice);
        proprietaAereo.add(a.getIdAereo());
        proprietaAereo.add(a.getModello());
        proprietaAereo.add(Integer.toString(a.getnPosti()));
        return proprietaAereo;
    }
    public String[] getAereo(int i)throws Exception{
        if(i == -1)
            throw new Exception("aereo non selezionato");
        Aereo a = aerei.get(i);
        String[] aereo = new String[3];
        aereo[0] = a.getIdAereo();
        aereo[1] = a.getModello();
        aereo[2] = String.valueOf(a.getnPosti());
        return aereo;
    }
    public void rimuoviAereo(int i)throws Exception{
        if(i == -1)
            throw new Exception("nessun aereo selezionato");
        aerei.remove(i);
    }
    public void dbg(){ //funzione debug
        System.out.println("dbg");
    }
    public void creaVolo(String idVolo,
                         String destinazione,
                         String durata,
                         Integer idPilota,
                         Integer idCoPilota,
                         Integer idHostess1,
                         Integer idHostess2,
                         Integer idAereo) throws ChiaveException, AuthenticationException{
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
        if(idPilota < 0)
            throw new InvalidParameterException("manca il pilota");
        if(idCoPilota < 0)
            throw new InvalidParameterException("manca il copilota");
        if(idHostess1 < 0)
            throw new InvalidParameterException("manca la prima hostess");
        if(idHostess2 < 0)
            throw new InvalidParameterException("manca la seconda hostess");
        if(piloti.get(idPilota).equals(piloti.get(idCoPilota)))
            throw new ChiaveException("inserire due piloti distinti");
        if(hostess.get(idHostess1).equals(hostess.get(idHostess2)))
            throw new ChiaveException("inserire due hostess distinti");
        for (Volo volo : voli){
            if(volo.getIdVolo().equals(idVolo))
                throw new IllegalArgumentException("idVolo già in uso");
        }
        if(idAereo < 0)
            throw new InvalidParameterException("manca l'aereo");
        voli.add(new Volo(idVolo, destinazione, durataInt, piloti.get(idPilota), piloti.get(idCoPilota),
                hostess.get(idHostess1), hostess.get(idHostess2), aerei.get(idAereo)));

    }

    public ArrayList<String> getListaVoli(){
        ArrayList<String> listaVoli = new ArrayList<>();
        for (Volo volo : voli){
            listaVoli.add(volo.getIdVolo() + " " + volo.getDestinazione());
        }
        return listaVoli;
    }
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
    public void rimuoviVolo(int i)throws Exception{
        if(i == -1)
            throw new Exception("nessun volo selezionato");
        voli.remove(i);
    }
    public void creaPrenotazione(String idPrenotazione,
                                 Integer idCliente,
                                 Integer idVolo,
                                 String posto,
                                 String classe) throws ChiaveException, AuthenticationException,InvalidParameterException{
            if(idPrenotazione.isBlank())
                throw new InvalidParameterException("idPrenotazione non inserito");
            if(idCliente < 0)
                throw new InvalidParameterException("cliente non inserito");
            if(idVolo < 0 )
                throw new InvalidParameterException("volo non inserito");
            if(posto.isBlank())
                throw new InvalidParameterException("posto non inserito");
            if(classe.isBlank())
                throw new InvalidParameterException("classe non inserita");
            for(Prenotazione p : prenotazioni) {
                if(p.getIdPrenotazione().equals(idPrenotazione))
                    throw new ChiaveException("chiave già usata");
                if(p.getVolo().getIdVolo().equals(voli.get(idVolo).getIdVolo()) && p.getPosto().equals(posto))
                    throw new ChiaveException("posto già usato");
            }
            prenotazioni.add(new Prenotazione(idPrenotazione, clienti.get(idCliente), voli.get(idVolo), posto, classe));
    }

    public ArrayList<String> getListaPrenotazioni(){
        ArrayList<String> listaPrenotazioni = new ArrayList<>();
        for(Prenotazione prenotazione : prenotazioni){
            listaPrenotazioni.add(prenotazione.getIdPrenotazione() + " " + prenotazione.getClasse());
        }
        return listaPrenotazioni;
    }
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
    public void rimuoviPrenotazione(int i)throws Exception{
        if(i == -1)
            throw new Exception("nessuna prenotazione selezionato");
        prenotazioni.remove(i);
    }
}

