package org.example;

public class Persona {
    private String nomeCompleto;
    private String codiceFiscale;
    private int numeroDiCellulare;
    public Persona(String nomeCompleto, String codiceFiscale){
        this.nomeCompleto = nomeCompleto;
        this.codiceFiscale = codiceFiscale;
    }
    public Persona(String nomeCompleto, String codicefiscale, int numeroDiCellulare){
        this.nomeCompleto = nomeCompleto;
        this.codiceFiscale = codicefiscale;
        this.numeroDiCellulare = numeroDiCellulare;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public void setNumeroDiCellulare(int numeroDiCellulare){
        this.numeroDiCellulare = numeroDiCellulare;
    }
    public String getNomeCompleto(){
        return this.nomeCompleto;
    }
    public String getCodiceFiscale(){
        return this.codiceFiscale;
    }
    public int getNumeroDiCellulare(){
        return this.numeroDiCellulare;
    }
}
