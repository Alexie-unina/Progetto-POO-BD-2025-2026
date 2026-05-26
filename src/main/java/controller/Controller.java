package controller;

import model.Aereo;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class  Controller {

    private ArrayList<Aereo> aerei = new ArrayList<Aereo>();

    public void exit(){
        System.exit(0);
    }
    /* login non inserito
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
}
