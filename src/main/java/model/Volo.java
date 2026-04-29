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
    public void setAereo(Aereo aereo){
        this.aereo = aereo;
    }
    public String getIdVolo(){
        return idVolo;
    }
    public String getDestinazione(){
        return destinazione;
    }
    public int getDurata(){
        return durata;
    }
    public Pilota getPilota(){
        return piloti[0];
    }
    public Pilota getCoPilota(){
        return piloti[1];
    }
    public Hostess getHostess1(){
        return hostess[0];
    }
    public Hostess getHostess2(){
        return hostess[1];
    }
    public Aereo getAereo() {return aereo;}
}
