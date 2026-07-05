package model;


public class Cliente extends Utente {
    private final String idCliente;

    /**
     * Costruttore in caso non ci sia numero di cellulare
     *
     * @see Utente
     * @param login
     * @param password
     * @param nomeCompleto
     * @param codiceFiscale
     * @param idCliente
     */
    public Cliente(String login,String password,String nomeCompleto, String codiceFiscale, String idCliente){
        super(login, password, nomeCompleto, codiceFiscale);
        this.idCliente = idCliente;
    }

    /**
     * Costruttore in caso ci sia numero di cellulare
     *
     * @see Utente
     * @param login
     * @param password
     * @param nomeCompleto
     * @param codiceFiscale
     * @param numeroDiCellulare
     * @param idCliente
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
