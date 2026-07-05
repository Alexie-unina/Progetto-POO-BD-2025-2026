package model;

public class Prenotazione {
    private final String idPrenotazione;
    private Cliente cliente;
    private Volo volo;
    private String posto;

    /**
     * Classe di prenotazione
     * Un Enum per la classe di prenotazione
     *  Opzioni Disponibili: Economy, EconomyPlus, Business, Prima
     */
    private enum ClassePrenotazione {
        ECONOMY,
        ECONOMYPLUS,
        BUSINESS,
        PRIMA
    }
    private ClassePrenotazione classePrenotazione;
//    public void setIdPrenotazione(String idPrenotazione){
//        this.idPrenotazione = idPrenotazione;
//    }
//    public void setCliente(Cliente cliente){
//        this.cliente = cliente;
//    }
//    public void setVolo(Volo volo) {
//        this.volo = volo;
//    }


    /**
     * Restituisce l'identificativo unico della prenotazione.
     *
     * @return una stringa che rappresenta l'ID della prenotazione.
     */
    public String getIdPrenotazione(){
        return idPrenotazione;
    }
    /**
     * Restituisce il cliente associato alla prenotazione.
     *
     * @return il cliente associato alla prenotazione
     */
    public Cliente getCliente(){
        return cliente;
    }
    /**
     * Restituisce il volo associato alla prenotazione.
     *
     * @return il volo associato, un'istanza della classe Volo.
     */
    public Volo getVolo(){
        return volo;
    }

    /**
     * Restituisce il posto assegnato alla prenotazione.
     *
     * @return il posto assegnato come stringa
     */
    public String getPosto() {return posto;}

    /**
     * Restituisce la classe di prenotazione
     * @return la classe di prenotazione
     */
    public String getClasse(){
        return classePrenotazione.name();
    }

    /**
     * Costruttore della classe Prenotazione.
     * Crea un'istanza di prenotazione specificando i dettagli del volo, il cliente associato,
     * il posto e la classe di prenotazione scelta.
     *
     * @author Alessandro Pizzi
     * @author Emanuele Servillo
     * @param idPrenotazione   l'identificativo unico della prenotazione.
     * @param cliente          il cliente associato alla prenotazione.
     * @param volo             il volo al quale si riferisce la prenotazione.
     * @param posto            il posto assegnato relativo alla prenotazione.
     * @param classePrenotazione la classe di prenotazione (Economy, EconomyPlus, Business, Prima).
     */
    public Prenotazione(String idPrenotazione, Cliente cliente,Volo volo, String posto,String classePrenotazione){
        this.idPrenotazione = idPrenotazione;
        this.cliente = cliente;
        this.volo = volo;
        this.posto = posto;
        System.out.println(classePrenotazione);
        this.classePrenotazione = ClassePrenotazione.valueOf(classePrenotazione.toUpperCase());
    }
}
