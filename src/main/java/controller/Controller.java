package controller;

import exceptions.ChiaveException;
import exceptions.ParameterMissingException;
import model.Cliente;

import javax.naming.AuthenticationException;
import model.Aereo;
import model.Pilota;
import model.Hostess;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class  Controller {

    private ArrayList<Cliente> clienti = new ArrayList<Cliente>();
    private ArrayList<Aereo>   aerei = new ArrayList<Aereo>();
    private ArrayList<Pilota>  piloti = new ArrayList<Pilota>();
    private ArrayList<Hostess> hostess = new ArrayList<Hostess>();


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

    public void stampaClienti(){ //Per debug
        for (Cliente cliente : clienti){
            System.out.println(cliente.getLogin() + cliente.getNomeCompleto());
        }
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

    public void stampaPiloti(){ //Per debug
        for (Pilota pilota : piloti){
            System.out.println(pilota.getLogin() + pilota.getNomeCompleto());
        }
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
    public ArrayList<Hostess> getHostess(){
        return hostess;
    }

    public void stampaHostess(){ //Per debug
        for (Hostess hostess : hostess){
            System.out.println(hostess.getLogin() + hostess.getNomeCompleto());
        }
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

    public ArrayList<Aereo> getAerei(){
        return aerei;
    }

    public void dbg(){ //funzione debug
        System.out.println("dbg");
    }
}
