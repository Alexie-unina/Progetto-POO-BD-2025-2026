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
    public void setModello(String modello){
        this.modello = modello;
    }
    public void setNPosti(int nPosti){
        this.nPosti = nPosti;
    }
    public String getIdAereo(){
        return idAereo;
    }
    public String getModello(){
        return modello;
    }
    public int getnPosti(){
        return nPosti;
    }
}
