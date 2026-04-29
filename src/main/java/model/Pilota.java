package model;

public class Pilota extends Utente {
    private double salario;
    public Pilota(String login,String password, String codiceFiscale, double salario){
        super(login,password,codiceFiscale);
        this.salario = salario;
    }
    public Pilota(String login,String password, String codiceFiscale, String numeroDiCellulare,double salario){
        super(login,password,codiceFiscale,numeroDiCellulare);
        this.salario = salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario(){
        return salario;
    }
}
