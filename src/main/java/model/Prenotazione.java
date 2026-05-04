package model;

public class Prenotazione {
    private final String idPrenotazione;
    private Cliente cliente;
    private Volo volo;
    private String posto;
    private enum ClassePrenotazione {
        ECONOMY,
        ECONOMYPLUS,
        BUSINESS,
        PRIMA;
    }
    private ClassePrenotazione classePrenotazione;
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
    public String getClasse(){
        return classePrenotazione.name();
    }
    public Prenotazione(String idPrenotazione, Cliente cliente,Volo volo, String posto,String classePrenotazione){
        this.idPrenotazione = idPrenotazione;
        this.cliente = cliente;
        this.volo = volo;
        this.posto = posto;
        this.classePrenotazione = ClassePrenotazione.valueOf(classePrenotazione.toUpperCase());
    }
}
