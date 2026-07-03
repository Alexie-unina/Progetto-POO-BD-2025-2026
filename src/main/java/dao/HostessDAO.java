package dao;

import database.ConnessioneDatabase;
import model.Hostess;
import model.Pilota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostessDAO {
            Connection connection;
    public HostessDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean salvaHostess(Hostess h) throws SQLException {
        String query = """
                INSERT INTO Hostess (login,password,nomeCompleto,codiceFiscale,numeroCellulare,idHostess,salario)
                VALUES 	(?,?,?,?,?,?,?)
                    ON CONFLICT (idHostess) DO UPDATE
                            SET login               =   EXCLUDED.login,
                                password            =   EXCLUDED.password,
                                nomeCompleto        =   EXCLUDED.nomeCompleto,
                                codiceFiscale       =   EXCLUDED.codiceFiscale,
                                numeroCellulare     =   EXCLUDED.numeroCellulare,
                                salario             =   EXCLUDED.salario;
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,h.getLogin());
            ps.setString(2,h.getPassword());
            ps.setString(3,h.getNomeCompleto());
            ps.setString(4,h.getCodiceFiscale());
            ps.setString(5,h.getNumeroDiCellulare());
            ps.setString(6,h.getIdHostess());
            ps.setDouble(7,h.getSalario());
            boolean res = ps.execute();
            
            return res;
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }

    }
    public Hostess getHostess(String idHostess) throws SQLException {
        String query = """
                SELECT login,nomeCompleto,codiceFiscale,numeroCellulare,idHostess,salario
                FROM hostess
                WHERE idHostess = '?';
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idHostess);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new SQLDataException("Hostess Non Trovato");
            }
            
            return new Hostess(rs.getString(1),
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

    public List<Hostess> getListaHostess() throws SQLException {
        String query = """
                SELECT *
                FROM Hostess;
                """;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Hostess> hostess = new ArrayList<>();;
            while(rs.next()){
                hostess.add(new Hostess(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7)));
            }
            
            return hostess;

    }

    public boolean rimuoviHostess(String idHostess) throws SQLException {
        String query = """
                DELETE
                FROM HOSTESS
                WHERE (idHostess = ?);
                """;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,idHostess);

            return ps.execute();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
