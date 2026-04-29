package model;

public class Pilota extends Utente {
    private final String idPilota;
    private double salario;
    public Pilota(String login, String password, String nomeCompleto, String codiceFiscale, String idPilota, double salario){
        super(login, password, nomeCompleto, codiceFiscale);
        this.idPilota = idPilota;
        this.salario = salario;
    }
    public Pilota(String login, String password, String nomeCompleto, String codiceFiscale, String numeroDiCellulare,
                  String idPilota, double salario){
        super(login, password, nomeCompleto, codiceFiscale, numeroDiCellulare);
        this.idPilota = idPilota;
        this.salario = salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario(){
        return salario;
    }
}
