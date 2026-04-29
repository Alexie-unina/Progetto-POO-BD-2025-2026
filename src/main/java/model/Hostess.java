package model;

public class Hostess extends Utente{
    private final String idHostess;
    public Hostess(String login,String password,String nomeCompleto, String codiceFiscale, String idHostess){
        super(login,password, nomeCompleto, codiceFiscale);
        this.idHostess = idHostess;
    }
    public Hostess(String login,String password, String nomeCompleto,  String codiceFiscale, String numeroDiCellulare, String idHostess){
        super(login,password, nomeCompleto, codiceFiscale, numeroDiCellulare);
        this.idHostess = idHostess;
    }
     public String getIdHostess(){
        return idHostess;
    }
}
