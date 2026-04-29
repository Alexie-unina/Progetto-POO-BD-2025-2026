package model;

public class Hostess extends Utente{
    private String idHostess;
    public Hostess(String login,String password, String codiceFiscale, String idHostess){
        super(login,password, codiceFiscale);
        this.idHostess = idHostess;
    }
    public Hostess(String login,String password, String codiceFiscale, String numeroDiCellulare, String idHostess){
        super(login,password, codiceFiscale, numeroDiCellulare);
        this.idHostess = idHostess;
    }
    public void setIdHostess(String idHostess){
        this.idHostess = idHostess;
    }
    public String getIdHostess(){
        return this.idHostess;
    }
}
