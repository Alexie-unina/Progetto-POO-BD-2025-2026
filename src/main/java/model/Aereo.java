package model;


public class Aereo {
    private final String idAereo;
    private String modello;
    private int nPosti;


    public Aereo(String idAereo, String modello, int nPosti){
        this.idAereo = idAereo;
        this.modello = modello;
        this.nPosti = nPosti;
    }

    /**
     * Imposta il modello.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param modello il modello
     */
    public void setModello(String modello){
        this.modello = modello;
    }

    /**
     * Imposta il nPosti
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param nPosti il nPosti
     */
    public void setNPosti(int nPosti){
        this.nPosti = nPosti;
    }

    /**
     * Restituisce L'idAereo
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa idAereo
     */
    public String getIdAereo(){
        return idAereo;
    }

    /**
     * Restituisce modello
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa modello
     */
    public String getModello(){
        return modello;
    }

    /**
     * Restituisce nPosti
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return l'intero nPosti
     */
    public int getnPosti(){
        return nPosti;
    }
}
