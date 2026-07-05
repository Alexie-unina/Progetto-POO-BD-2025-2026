package model;

public class Hostess extends Utente{
    private final String idHostess;
    private double salario;

    /**
     * Costruttore in caso non ci sia numero di cellulare
     *
     * @author Alessandro Pizzi
     * @author Emanuele Servillo
     * @see Utente
     * @param login  login dell'hostess
     * @param password password dell'hostess
     * @param nomeCompleto nome completo dell'hostess
     * @param codiceFiscale codice fiscale dell'hostess
     * @param idHostess identificativo univoco dell'hostess
     * @param salario salario dell'hostess
     */
    public Hostess(String login,String password,String nomeCompleto, String codiceFiscale, String idHostess,double salario){
        super(login,password, nomeCompleto, codiceFiscale);
        this.idHostess = idHostess;
        this.salario = salario;
    }
    /**
     * Costruttore in caso ci sia numero di cellulare
     *
     * @author Alessandro Pizzi
     * @author Emanuele Servillo
     * @see Utente
     * @param login login dell'hostess
     * @param password password dell'hostess
     * @param nomeCompleto nome completo dell'hostess
     * @param codiceFiscale codice fiscale dell'hostess
     * @param idHostess identificativo univoco dell'hostess
     * @param salario salario dell'hostess
     * @param numeroDiCellulare numero di cellulare dell'hostess
     */
    public Hostess(String login,String password, String nomeCompleto,  String codiceFiscale, String numeroDiCellulare, String idHostess,double salario){
        super(login,password, nomeCompleto, codiceFiscale, numeroDiCellulare);
        this.idHostess = idHostess;
        this.salario = salario;
    }

    /**
     * Restituisce l'id dell'hostess
     * @return idHostess
     */
     public String getIdHostess(){
        return idHostess;
    }

    /**
     * Imposta il salario per l'hostess.
     *
     * @param salario il nuovo valore del salario da assegnare
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario(){
        return salario;
    }
}
