package dao;

import database.ConnessioneDatabase;
import model.Hostess;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {
    Connection connection = ConnessioneDatabase.getInstance().connection;

    public PrenotazioneDAO() throws SQLException {
    }

    /**
     * Salva una prenotazione nel database
     *
     * <p>
     *     <H>Esempio di utilizzo:</H>
     * {@snippet :
     *
     *   PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
     *   Prenotazione p = new Prenotazione("1234567890",cliente,volo,"A1", "Economy");
     *   prenotazioneDAO.salvaPrenotazione(p);
     *   prenotazioneDAO.closeConnection();
     *  }
     *  </p>
     *
     * @author Alessandro Pizzi
     * @author Emanuele Servillo
     * @see Prenotazione
     * @param p {@link Prenotazione} da salvare
     * @return se l'operazione e andata a buon fine
     * @throws SQLException se si verifica un errore durante la connessione al database oppure durante l'esecuzione della query
     */
    public boolean salvaPrenotazione(Prenotazione p) throws SQLException {
        System.out.println("salvataggio prenotazione");
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


    /**
     * Recupera una Prenotazione dal database
     * @author Alessandro Pizzi
     * @author Emanuele Servillo
     * @see Prenotazione
     *
     * @param idPrenotazione id della {@link Prenotazione} da recuperare
     * @return l'oggetto {@link Prenotazione} con l'id specificato
     * @throws SQLException se si verifica un errore durante la connessione al database oppure durante l'esecuzione della query
     */
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

    /**
     * Recupera tutte le prenotazioni dal database e le restituisce in una lista
     * @author Alessandro Pizzi
     * @author Emanuele Servillo
     * @see Prenotazione
     * @see List
     *
     * @return una lista di {@link Prenotazione} contenente tutte le prenotazioni presenti nel database
     * @throws SQLException se si verifica un errore durante la connessione al database oppure durante l'esecuzione della query
     */
    public List<Prenotazione> getListaPrenotazioni() throws SQLException {
        String query = """
                SELECT *
                FROM Prenotazione;
                """;
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Prenotazione> prenotazioni = new ArrayList<>();
        while (rs.next()){
            prenotazioni.add(new Prenotazione(
                    rs.getString(1),
                    new ClienteDAO().getCliente(rs.getString(2)),
                    new VoloDAO().getVolo(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }
        return prenotazioni;
    }

    /**
     * Rimuove una prenotazione dal database in base all'id fornito
     * @see Prenotazione
     * @see Hostess
     *
     * @author Alessandro Pizzi
     * @author Emanuele Servillo
     * @param idPrenotazione id della {@link Prenotazione} da rimuovere
     * @return se l'operazione e andata a buon fine
     * @throws SQLException se si verifica un errore durante la connessione al database oppure durante l'esecuzione della query
     */
    public boolean rimuoviPrenotazione(String idPrenotazione) throws SQLException {
        String query = """
                DELETE
                FROM Prenotazione
                WHERE (idPrenotazione = ?);
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idPrenotazione);

            return ps.execute();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
