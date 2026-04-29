package org.example;

public class Prenotazione {
    private String idPrenotazione;
    private Cliente cliente;
    private Volo volo;
    public void setIdPrenotazione(String idPrenotazione){
        this.idPrenotazione = idPrenotazione;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setVolo(Volo volo) {
        this.volo = volo;
    }
    public String getIdPrenotazione(){
        return this.idPrenotazione;
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    public Volo getVolo(){
        return this.volo;
    }

}
