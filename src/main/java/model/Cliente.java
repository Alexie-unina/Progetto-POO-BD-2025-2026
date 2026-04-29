package model;

public class Cliente extends Utente {
    private String idCliente;
    public Cliente(String login,String password, String codiceFiscale, String idCliente){
        super(login,password,codiceFiscale);
        this.idCliente = idCliente;
    }
    public Cliente(String login,String password, String codiceFiscale, String numeroDiCellulare, String idCliente){
        super(login,password, codiceFiscale, numeroDiCellulare);
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
