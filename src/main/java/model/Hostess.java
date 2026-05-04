package model;

public class Hostess extends Utente{
    private final String idHostess;
    private double salario;
    public Hostess(String login,String password,String nomeCompleto, String codiceFiscale, String idHostess,double salario){
        super(login,password, nomeCompleto, codiceFiscale);
        this.idHostess = idHostess;
        this.salario = salario;
    }
    public Hostess(String login,String password, String nomeCompleto,  String codiceFiscale, String numeroDiCellulare, String idHostess,double salario){
        super(login,password, nomeCompleto, codiceFiscale, numeroDiCellulare);
        this.idHostess = idHostess;
        this.salario = salario;
    }
     public String getIdHostess(){
        return idHostess;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario(){
        return salario;
    }
}
