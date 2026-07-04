package dao;

import database.ConnessioneDatabase;
import model.Aereo;
import model.Hostess;
import model.Volo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoloDAO {
    Connection connection = ConnessioneDatabase.getInstance().connection;

    public VoloDAO() throws SQLException {
    }

    public boolean salvaVolo(Volo v) throws SQLException {
        Connection connection = ConnessioneDatabase.getInstance().connection;

        String query = """
                INSERT INTO Volo (idVolo,destinazione,durata,idPilota,idCopilota,idHostess1,idHostess2,idAereo)
                VALUES(?,?,?,?,?,?,?,?);
                """;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Param count: " + ps.getParameterMetaData().getParameterCount());

            ps.setString(1,v.getIdVolo());
            ps.setString(2,v.getDestinazione());
            ps.setInt(3,v.getDurata());
            ps.setString(4,v.getPilota().getIdPilota());
            ps.setString(5,v.getCoPilota().getIdPilota());
            ps.setString(6,v.getHostess1().getIdHostess());
            ps.setString(7,v.getHostess2().getIdHostess());
            ps.setString(8,v.getAereo().getIdAereo());

            boolean res = ps.execute();
            return res;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public List<Volo> getListaVoli() throws SQLException {
        Connection connection = ConnessioneDatabase.getInstance().connection;

        String query = """
                SELECT *
                FROM Volo;
                """;

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Volo> voli = new ArrayList<>();
        while (rs.next()){
            voli.add(new Volo(
                rs.getString(1),
                rs.getString(2),
                rs.getInt(3),
                new PilotaDAO().getPilota(rs.getString(4)),
                new PilotaDAO().getPilota(rs.getString(5)),
                new HostessDAO().getHostess(rs.getString(6)),
                new HostessDAO().getHostess(rs.getString(7)),
                new AereoDAO().getAereo(rs.getString(8))
                    ));
        }
        return voli;
    }

    public Volo getVolo(String idvolo) throws SQLException {
        Connection connection = ConnessioneDatabase.getInstance().connection;
        String query = """
                SELECT * FROM VOLO WHERE idvolo = ?;
                """;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,idvolo);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
        return new Volo(rs.getString(1),
            rs.getString(2),
            rs.getInt(3),
            new PilotaDAO().getPilota(rs.getString(4)),
            new PilotaDAO().getPilota(rs.getString(5)),
            new HostessDAO().getHostess(rs.getString(6)),
            new HostessDAO().getHostess(rs.getString(7)),
            new AereoDAO().getAereo(rs.getString(8)));
        }else {
            throw new SQLException("Volo non trovato");
        }
    }

    public boolean rimuoviVolo(String idVolo) throws SQLException {
        String query = """
                DELETE
                FROM VOLO
                WHERE (idVolo = ?);
                """;
    try {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,idVolo);

        return ps.execute();
    } catch(Exception e) {
        throw new RuntimeException(e);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
