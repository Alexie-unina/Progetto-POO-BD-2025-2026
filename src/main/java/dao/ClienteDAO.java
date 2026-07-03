package dao;

import database.ConnessioneDatabase;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    Connection connection;
    public ClienteDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean salvaCliente(Cliente c) throws SQLException {
        String query = """
                INSERT INTO Cliente (login,password,nomeCompleto,codiceFiscale,numeroCellulare,idCliente)
                VALUES 	(?,?,?,?,?,?)
                    ON CONFLICT (idCliente) DO UPDATE
                        SET login               =   EXCLUDED.login,
                            password            =   EXCLUDED.password,
                            nomeCompleto        =   EXCLUDED.nomeCompleto,
                            codiceFiscale       =   EXCLUDED.codiceFiscale,
                            numeroCellulare     =   EXCLUDED.numeroCellulare;
                """;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,c.getLogin());
        ps.setString(2,c.getPassword());
        ps.setString(3,c.getNomeCompleto());
        ps.setString(4,c.getCodiceFiscale());
        ps.setString(5,c.getNumeroDiCellulare());
        ps.setString(6,c.getIdCliente());
        boolean res = ps.execute();
        return res;
    }
    public Cliente getCliente(String idCliente) throws SQLException {
        String query = """
                SELECT login,nomeCompleto,codiceFiscale,numeroCellulare,idCliente
                FROM cliente
                WHERE idCliente = ?;
                """;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,idCliente);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            throw new SQLDataException("Cliente Non Trovato");
        }
        return new Cliente(rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6));
    }

    public List<Cliente> getClienti() throws SQLException {
        String query = """
                SELECT *
                FROM Cliente;
                """;

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Cliente> clienti = new ArrayList<>();;
        while(rs.next()){
        clienti.add(new Cliente(rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6)));
        }
        return clienti;
    }

    public boolean rimuoviCliente(String idCliente) throws SQLException {
        String query = """
                DELETE
                FROM CLIENTE
                WHERE (idCliente = ?);
                """;

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,idCliente);
        return ps.execute();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
