package model;


public class Volo {
    private String idVolo;
    private String destinazione;
    private int durata;
    private final Pilota[] piloti = new Pilota[2];
    private final Hostess[] hostess = new Hostess[2];
    private Aereo aereo;


    public Volo(String idVolo, String destinazione, int durata, Pilota pilota, Pilota copilota, Hostess hostess1, Hostess hostess2,
                Aereo aereo){
        this.idVolo = idVolo;
        this.destinazione = destinazione;
        this.durata = durata;
        this.piloti[0] = pilota;
        this.piloti[1] = copilota;
        this.hostess[0] = hostess1;
        this.hostess[1] = hostess2;
                this.aereo = aereo;
    }

    /**
     * Imposta id volo.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param idVolo l'id volo
     */
    public void setIdVolo(String idVolo){
                this.idVolo = idVolo;
    }

    /**
     * Imposta destinazione.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param destinazione la destinazione
     */
    public void setDestinazione(String destinazione){
                this.destinazione = destinazione;
    }

    /**
     * Imposta durata.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param durata la durata
     */
    public void setDurata(int durata){
                this.durata = durata;
    }

    /**
     * Imposta piloti.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param pilota   il pilota
     * @param copilota il copilota
     */
    public void setPiloti(Pilota pilota, Pilota copilota){
        this.piloti[0] = pilota;
        this.piloti[1] = copilota;
    }

    /**
     * Imposta hostess.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param hostess1 l'hostess 1
     * @param hostess2 l'hostess 2
     */
    public void setHostess(Hostess hostess1, Hostess hostess2){
        this.hostess[0] = hostess1;
        this.hostess[1] = hostess2;
    }

    /**
     * Imposta aereo.
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param aereo l'aereo
     */
    public void setAereo(Aereo aereo){
        this.aereo = aereo;
    }

    /**
     * Restituisce L'idVolo
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la Stringa idVolo
     */
    public String getIdVolo(){
        return idVolo;
    }

    /**
     * Restituisce la destinazione
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la stringa destinazione
     */
    public String getDestinazione(){
        return destinazione;
    }

    /**
     * Restituisce la durata
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return l'intero durata
     */
    public int getDurata(){
        return durata;
    }

    /**
     * Restituisce il pilota
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return il pilota
     */
    public Pilota getPilota(){
        return piloti[0];
    }

    /**
     * Restitusice il copilota
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return er pilota
     */
    public Pilota getCoPilota(){
        return piloti[1];
    }

    /**
     * Restituisce l'hostess 1
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return l'hostess 1
     */
    public Hostess getHostess1(){
        return hostess[0];
    }

    /**
     * Restituisce l'hostess 2
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return l'hostess 2
     */
    public Hostess getHostess2(){
        return hostess[1];
    }

    /**
     * Restituisce l'aereo
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return L'aereo
     */
    public Aereo getAereo() {return aereo;}
}
