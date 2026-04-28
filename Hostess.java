package org.example;

public class Hostess extends Persona{
    private String idHostess;
    public Hostess(String nomeCompleto, String codiceFiscale, String idHostess){
        super(nomeCompleto, codiceFiscale);
        this.idHostess = idHostess;
    }
    public Hostess(String nomeCompleto, String codiceFiscale, int numeroDiCellulare, String idHostess){
        super(nomeCompleto, codiceFiscale, numeroDiCellulare);
        this.idHostess = idHostess;
    }
    public void setIdHostess(String idHostess){
        this.idHostess = idHostess;
    }
    public String getIdHostess(){
        return this.idHostess;
    }
}
