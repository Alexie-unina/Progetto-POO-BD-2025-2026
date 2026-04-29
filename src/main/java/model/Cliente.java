package model;

public class Cliente extends Utente {
    private final String idCliente;
    public Cliente(String login,String password,String nomeCompleto, String codiceFiscale, String idCliente){
        super(login, password, nomeCompleto, codiceFiscale);
        this.idCliente = idCliente;
    }
    public Cliente(String login,String password, String nomeCompleto, String codiceFiscale, String numeroDiCellulare, String idCliente){
        super(login, password,nomeCompleto,  codiceFiscale, numeroDiCellulare);
        this.idCliente = idCliente;
    }
    public String getIdCliente(){
        return idCliente;
    }
    public void prenotaVolo(String idVolo){
        //da farsi
        System.out.println("volo prenotato");
    }
    public void chiamaSupporto(){
        System.out.println("MAYDAYMAYDAY");
    }
}
