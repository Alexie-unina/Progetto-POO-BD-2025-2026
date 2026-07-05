package model;


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

    /**
     * Imposta nome completo.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param nomeCompleto il nome completo
     */
    public void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }

    /**
     * Imposta codice fiscale.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param codiceFiscale er fiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Imposta numero di cellulare.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param numeroDiCellulare il numero di celluare
     */
    public void setNumeroDiCellulare(String numeroDiCellulare){
        this.numeroDiCellulare = numeroDiCellulare;
    }

    /**
     * Restituisce il codice fiscale
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa codice fiscale
     */
    public String getCodiceFiscale(){
        return codiceFiscale;
    }

    /**
     * Restituisce il nome completo
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa nome completo
     */
    public String getNomeCompleto(){
        return nomeCompleto;
    }

    /**
     * Restituisce il numero di cellulare
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa numero di cellulare
     */
    public String getNumeroDiCellulare(){
        return numeroDiCellulare;
    }

    /**
     * Restituisce il login.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Imposta password.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param password la password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce la password
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa password
     */
    public String getPassword(){return password;}
}
