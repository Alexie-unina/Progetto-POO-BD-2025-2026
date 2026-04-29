package model;

public class Prenotazione {
    private final String idPrenotazione;
    private Cliente cliente;
    private Volo volo;
    private String posto;
//    public void setIdPrenotazione(String idPrenotazione){
//        this.idPrenotazione = idPrenotazione;
//    }
//    public void setCliente(Cliente cliente){
//        this.cliente = cliente;
//    }
//    public void setVolo(Volo volo) {
//        this.volo = volo;
//    }
    public String getIdPrenotazione(){
        return idPrenotazione;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public Volo getVolo(){
        return volo;
    }
    public String getPosto() {return posto;}

    public Prenotazione(String idPrenotazione, Cliente cliente,Volo volo, String posto){
        this.idPrenotazione = idPrenotazione;
        this.cliente = cliente;
        this.volo = volo;
        this.posto = posto;
    }
}
