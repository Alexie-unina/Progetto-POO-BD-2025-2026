package model;


public class Cliente extends Utente {
    private final String idCliente;

    /**
     * Costruttore in caso non ci sia numero di cellulare
     *
     * @see Utente
     * @param login  login del cliente
     * @param password password del cliente
     * @param nomeCompleto nome completo del cliente
     * @param codiceFiscale codice fiscale del cliente
     * @param idCliente identificativo univoco del cliente
     */
    public Cliente(String login,String password,String nomeCompleto, String codiceFiscale, String idCliente){
        super(login, password, nomeCompleto, codiceFiscale);
        this.idCliente = idCliente;
    }

    /**
     * Costruttore in caso ci sia numero di cellulare
     *
     * @see Utente
     * @param login  login del cliente
     * @param password password del cliente
     * @param nomeCompleto nome completo del cliente
     * @param codiceFiscale codice fiscale del cliente
     * @param numeroDiCellulare numero di cellulare del cliente
     * @param idCliente identificativo univoco del cliente
     */
    public Cliente(String login,String password, String nomeCompleto, String codiceFiscale, String numeroDiCellulare, String idCliente){
        super(login, password,nomeCompleto,  codiceFiscale, numeroDiCellulare);
        this.idCliente = idCliente;
    }

    /**
     * Restituisce l'id del cliente
     *
     * @return idCliente
     */
    public String getIdCliente(){
        return idCliente;
    }

    /**
     * Prenota un volo
     *
     * @param idVolo id del volo da prenotare
     */

    public void prenotaVolo(String idVolo){
        //da farsi
        System.out.println("volo prenotato");
    }
//    public void chiamaSupporto(){
//        System.out.println("MAYDAYMAYDAY");
//    }
}
