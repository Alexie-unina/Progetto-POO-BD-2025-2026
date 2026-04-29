package model;

/**
 * The type Utente.
 */
public class Utente {
    private final String login;
    private String password;
    private String codiceFiscale;
    private String numeroDiCellulare;
    public Utente(String login,String password, String codiceFiscale){
        this.login= login;
        this.password = password;
        this.codiceFiscale = codiceFiscale;
    }
    public Utente(String login,String password, String codicefiscale, String numeroDiCellulare){
        this.login = login;
        this.password = password;
        this.codiceFiscale = codicefiscale;
        this.numeroDiCellulare = numeroDiCellulare;
    }
    public Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public void setNumeroDiCellulare(int numeroDiCellulare){
        this.numeroDiCellulare = numeroDiCellulare;
    }
    public String getCodiceFiscale(){
        return this.codiceFiscale;
    }
    public int getNumeroDiCellulare(){
        return this.numeroDiCellulare;
    }

    public String getLogin() {
        return login;
    }
}
