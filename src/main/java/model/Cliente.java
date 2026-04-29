package org.example;

public class Cliente extends Persona {
    private String idCliente;
    public Cliente(String nomeCompleto, String codiceFiscale, String idCliente){
        super(nomeCompleto, codiceFiscale);
        this.idCliente = idCliente;
    }
    public Cliente(String nomeCompleto, String codiceFiscale, int numeroDiCellulare, String idCliente){
        super(nomeCompleto, codiceFiscale, numeroDiCellulare);
        this.idCliente = idCliente;
    }
    public void setIdCliente(String idCliente){
        this.idCliente = idCliente;
    }
    public String getIdCliente(){
        return this.idCliente;
    }
    public void prenotaVolo(String idVolo){
        //da farsi
        System.out.println("volo prenotato");
    }
    public void chiamaSupporto(){
        System.out.println("MAYDAYMAYDAY");
    }
}
