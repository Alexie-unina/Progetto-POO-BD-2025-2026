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

    /**
     * Salva un Volo nella tabella Volo
     * <p>
     *    Esempio di utilizzo:
     * {@snippet :
     * VoloDAO voloDAO = new VoloDAO();
     * Volo v = new Volo(a, a, a, a, a, a, a, a);
     * voloDAO.salvaVolo();
     * voloDAO.closeConnection();
     *
     * }
     * </p>
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     *
     * @param v il {@link Volo} da salvare
     * @see Volo
     * @return se l'operazione è andata a buon fine
     * @throws SQLException se ci sono stati problemi con la connessione o l'esecuzione della query
     */
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

    /**
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @return la lista dei Voli {@link Volo}
     * @see Volo
     * @see HostessDAO
     * @see PilotaDAO
     * @see AereoDAO
     * @throws SQLException se ci sono stati problemi con la connessione o l'esecuzione della query
     */
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

    /**
     * Recupera un Volo dalla tabella Volo
     * @param idvolo id del {@link Volo} da recuperare
     * @see Volo
     * @see PilotaDAO
     * @see HostessDAO
     * @see AereoDAO
     * @return il Volo recuperato
     * @throws SQLException se ci sono stati problemi con la connessione o l'esecuzione della query
     */
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

    /**
     * Rimuove un Volo dalla tabella Volo
     * @param idVolo id del Volo da rimuovere
     * @return se l'operazione è andata a buon fine
     * @throws SQLException se ci sono stati problemi con la connessione o l'esecuzione della query
     */
    public boolean rimuoviVolo(String idVolo) throws SQLException {
        Connection connection = ConnessioneDatabase.getInstance().connection;
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

    /**
     * Mostra i Clienti prenotati per un Volo
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @param idVolo id del {@link Volo} da cui selezionare i Clienti
     * @see Volo
     * @return Stringhe contenenti login e nome dei clienti prenotati al Volo
     * @throws SQLException se ci sono stati problemi con la connessione o l'esecuzione della query
     */
    public String mostraClientiVolo(String idVolo) throws SQLException {
        Connection connection = ConnessioneDatabase.getInstance().connection;
        String query = """
                SELECT Cliente.idCliente, Cliente.nomeCompleto
                        FROM Cliente\s
                        JOIN PRENOTAZIONE
                          on cliente.idCliente = Prenotazione.idCliente
                        JOIN Volo
                          on Volo.idVolo = prenotazione.IdVolo
                        WHERE Prenotazione.idVolo = ?;
        """;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,idVolo);
        ResultSet rs = ps.executeQuery();
        String clienti = "";
        while(rs.next()){
            clienti += "\t" + rs.getString(1) + " " + rs.getString(2) + "\n";
        }
        return clienti;
    }

    /**
     * Chiude la connessione al database
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @throws SQLException se ci sono stati problemi con la connessione
     */
    public void closeConnection() throws SQLException {
        connection.close();
    }

}
