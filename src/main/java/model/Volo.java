package model;

public class Volo {
    private String idVolo;
    private String destinazione;
    private int durata;
    private final Pilota[] piloti = new Pilota[2];
    private final Hostess[] hostess = new Hostess[2];
    public Volo(String idVolo, String destinazione, int durata, Pilota pilota, Pilota copilota, Hostess hostess1, Hostess hostess2){
        this.idVolo = idVolo;
        this.destinazione = destinazione;
        this.durata = durata;
        this.piloti[0] = pilota;
        this.piloti[1] = copilota;
        this.hostess[0] = hostess1;
        this.hostess[1] = hostess2;
    }
    public void setIdVolo(String idVolo){
        this.idVolo = idVolo;
    }
    public void setDestinazione(String destinazione){
        this.destinazione = destinazione;
    }
    public void setDurata(int durata){
        this.durata = durata;
    }
    public void setPiloti(Pilota pilota, Pilota copilota){
        this.piloti[0] = pilota;
        this.piloti[1] = copilota;
    }
    public void setHostess(Hostess hostess1, Hostess hostess2){
        this.hostess[0] = hostess1;
        this.hostess[1] = hostess2;
    }
    public String getIdVolo(){
        return this.idVolo;
    }
    public String getDestinazione(){
        return this.destinazione;
    }
    public int getDurata(){
        return this.durata;
    }
    public Pilota getPilota(){
        return this.piloti[0];
    }
    public Pilota getCoPilota(){
        return this.piloti[1];
    }
    public Hostess getHostess1(){
        return this.hostess[0];
    }
    public Hostess getHostess2(){
        return this.hostess[1];
    }
}
