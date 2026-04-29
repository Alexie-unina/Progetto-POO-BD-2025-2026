package model;

/**
 * The type Utente.
 */
public abstract class Utente {
    private final String login;
    private String password;
    private String nomeCompleto;
    private String codiceFiscale;
    private String numeroDiCellulare;
    public Utente(String login,String password, String nomeCompleto, String codiceFiscale){
        this.login= login;
        this.password = password;
        this.nomeCompleto = nomeCompleto;
        this.codiceFiscale = codiceFiscale;
    }
    public Utente(String login, String password, String nomeCompleto, String codicefiscale, String numeroDiCellulare){
        this.login = login;
        this.password = password;
        this.nomeCompleto = nomeCompleto;
        this.codiceFiscale = codicefiscale;
        this.numeroDiCellulare = numeroDiCellulare;
    }
    public Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public void setNumeroDiCellulare(String numeroDiCellulare){
        this.numeroDiCellulare = numeroDiCellulare;
    }
    public String getCodiceFiscale(){
        return codiceFiscale;
    }
    public String getNomeCompleto(){
        return nomeCompleto; 
    }
    public String getNumeroDiCellulare(){
        return numeroDiCellulare;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
