package dao;

import database.ConnessioneDatabase;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {
    Connection connection = ConnessioneDatabase.getInstance().connection;

    public PrenotazioneDAO() throws SQLException {
    }

    public boolean salvaPrenotazione(Prenotazione p) throws SQLException {
        String query = """
                INSERT INTO Prenotazione (idPrenotazione,idCliente,idVolo,posto,classe)
                    VALUES (?,?,?,?,?);
                """;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,p.getIdPrenotazione());
        ps.setString(2,p.getCliente().getIdCliente());
        ps.setString(3,p.getVolo().getIdVolo());
        ps.setString(4,p.getPosto());
        ps.setString(5,p.getClasse());
        return ps.execute();
    }

    public Prenotazione getPrenotazione(String idPrenotazione) throws SQLException {
        String query = """
                SELECT *
                FROM Prenotazione
                WHERE idPrenotazione = ?;
                """;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,idPrenotazione);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            throw new SQLDataException("Prenotazione Non Trovato");
        }
        return new Prenotazione(
                rs.getString(1),
                new ClienteDAO().getCliente(rs.getString(2)),
                new VoloDAO().getVolo(rs.getString(3)),
                rs.getString(4),
                rs.getString(5));

    }
    public List<String> getListaPrenotazioni() throws SQLException {
        String query = """
                SELECT idPrenotazione,posto
                FROM Prenotazione;
                """;
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> prenotazioni = new ArrayList<>();
        while (rs.next()){
            prenotazioni.add(rs.getString(1) + " " + rs.getString(2));
        }
        return prenotazioni;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
