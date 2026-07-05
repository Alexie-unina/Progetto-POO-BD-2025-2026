package dao;

import database.ConnessioneDatabase;
import model.Aereo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AereoDAO {
    Connection connection;
    public AereoDAO()  throws SQLException{
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * Salva un Aereo nella tabella Aereo
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     * @see Aereo
     * @param a {@link Aereo} da salvare
     * @return se l'operazione è andata a buon fine
     * @throws SQLException se ci sono stati problemi nella connessione o nell'esecuzione della query
     */
    public boolean salvaAereo(Aereo a) throws SQLException {
        String query = """
                INSERT INTO Aereo (idAereo,modello,nPosti)
                VALUES 	(?,?,?);
                """;
//                ON CONFLICT (idAereo) DO UPDATE
//                    SET modello = EXCLUDED.modello,
//                    nPosti = EXCLUDED.nPosti;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,a.getIdAereo());
            ps.setString(2,a.getModello());
            ps.setInt(3,a.getnPosti());

            boolean res = ps.execute();
            
            return res;
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }

    }

    /**
     * Recupera un Aereo dal database
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     * @param idAereo id dell'{@link Aereo} da recuperare
     * @see Aereo
     * @return L'aereo recuperato
     * @throws SQLException se ci sono stati problemi nella connessione o nell'esecuzione della query
     */
    public Aereo getAereo(String idAereo) throws SQLException {
        String query = """
                SELECT idAereo,modello,nPosti
                FROM Aereo
                WHERE idAereo = ?;
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idAereo);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new SQLDataException("Aereo Non Trovato");
            }
            
            return new Aereo(rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Errore nel database, controllare la console");
        }
    }

    /**
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     * @return la lista degli Aerei {@link Aereo}
     * @see Aereo
     * @throws SQLException se ci sono stati problemi nella connessione o nell'esecuzione della query
     */
    public List<Aereo> getListaAerei() throws SQLException {
        String query = """
                SELECT *
                FROM Aereo;
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Aereo> aerei = new ArrayList<>();;
            while(rs.next()){
                aerei.add(new Aereo(rs.getString(1),rs.getString(2),rs.getInt(3)));
            }
            
            return aerei;
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
    }

    /**
     * Rimuove un {@link Aereo} dalla tabella Aereo
     * @author Alessandro Pizzi
     * @author Emy Servillo
     *
     * @see Aereo
     * @param idAereo id dell'aereo da rimuovere
     * @return se l'operazione è andata a buon fine
     * @throws SQLException se ci sono stati problemi nella connessione o nell'esecuzione della query
     */
    public boolean rimuoviAereo(String idAereo) throws SQLException {
        String query = """
                DELETE
                FROM AEREO
                WHERE (idAereo = ?);
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idAereo);
            
            return ps.execute();
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
    }

    /**
     * Chiude la connessione col database
     * @author Alessandro Pizzi
     * @author Emy Servillo
     * @throws SQLException se ci sono stati problemi con la connessione
     */
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
