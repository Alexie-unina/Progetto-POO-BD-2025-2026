package org.example;

public class Pilota extends Persona{
    private String idPilota;
    public Pilota(String nomeCompleto, String codiceFiscale, String idPilota){
        super(nomeCompleto, codiceFiscale);
        this.idPilota = idPilota;
    }public Pilota(String nomeCompleto, String codiceFiscale, int numeroDiCellulare, String idPilota){
        super(nomeCompleto, codiceFiscale, numeroDiCellulare);
        this.idPilota = idPilota;
    }

    public void setIdPilota(String idPilota) {
        this.idPilota = idPilota;
    }
    public String getIdPilota(){
        return this.idPilota;
    }
}
