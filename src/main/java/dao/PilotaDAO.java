package dao;

import database.ConnessioneDatabase;
import model.Cliente;
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

    public boolean salvaPilota(Pilota p) throws SQLException {
        String query = """
                INSERT INTO Pilota (login,password,nomeCompleto,codiceFiscale,numeroCellulare,idPilota,salario)
                VALUES 	(?,?,?,?,?,?,?)
                    ON CONFLICT (idPilota) DO UPDATE
                            SET login               =   EXCLUDED.login,
                                password            =   EXCLUDED.password,
                                nomeCompleto        =   EXCLUDED.nomeCompleto,
                                codiceFiscale       =   EXCLUDED.codiceFiscale,
                                numeroCellulare     =   EXCLUDED.numeroCellulare,
                                salario             =   EXCLUDED.salario;
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,p.getLogin());
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
    public Pilota getPilota(String idPilota) throws SQLException {
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

    public List<Pilota> getPiloti() throws SQLException {
        String query = """
                SELECT *
                FROM Pilota
                """;

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Pilota> piloti = new ArrayList<>();;
        while(rs.next()){
            piloti.add(new Pilota(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getDouble(7)));
        }
        return piloti;
    }

    public boolean rimuoviPilota(String idPilota) throws SQLException {
        String query = """
                DELETE
                FROM PILOTA
                WHERE (idPilota = ?);
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idPilota);
            
            int i = ps.executeUpdate();
            System.out.println(i);
            return true;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
