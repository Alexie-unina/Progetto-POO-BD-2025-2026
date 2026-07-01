package dao;

import database.ConnessioneDatabase;
import model.Pilota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotaDAO {
    Connection connection;

    public PilotaDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean salvaPilota(Pilota p){
        String query = """
                INSERT INTO Pilota (login,password,nomeCompleto,codiceFiscale,numeroCellulare,idPilota,salario)
                	VALUES 	(?,?,?,?,?,?,?);
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,p.getIdPilota());
            ps.setString(2,p.getPassword());
            ps.setString(3,p.getNomeCompleto());
            ps.setString(4,p.getCodiceFiscale());
            ps.setString(5,p.getNumeroDiCellulare());
            ps.setString(6,p.getIdPilota());
            ps.setDouble(7,p.getSalario());
            boolean res = ps.execute();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Pilota getPilota(String idPilota){
        String query = """
                SELECT login,nomeCompleto,codiceFiscale,numeroCellulare,idPilota,salario
                FROM pilota
                WHERE idPilota = '?';
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idPilota);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new SQLDataException("Pilota Non Trovato");
            }

            return new Pilota(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getDouble(7));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getNomiPiloti(){
        String query = """
                SELECT login,nomeCompleto
                FROM pilota;
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<String> piloti = new ArrayList<>();;
            while(rs.next()){
                piloti.add(rs.getString(1) + " " + rs.getString(2));
            }
            return piloti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
