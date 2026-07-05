package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La classe ConnessioneDatabase implementa il pattern Singleton per gestire
 * la connessione al database PostgreSQL utilizzato dall'applicazione.
 * Garantisce che ci sia una sola istanza attiva della connessione durante
 * il ciclo di vita dell'applicazione. Fornisce inoltre metodi per accedere
 * alla connessione stessa in modo sicuro.
 * @author Alessandro Pizzi
 * @author Emanuele Servillo
 * @see Connection
 * @see DriverManager
 * @see SQLException
 *
 */
public class ConnessioneDatabase {

	// ATTRIBUTI
	private static ConnessioneDatabase instance;
	public Connection connection = null;
	private String nome = "postgres";
	private String password = "PasswordPerIlProgettoPOODB2026!?$";
	private String url = "jdbc:postgresql://101.58.71.46:5432/aereoporto";
	private String driver = "org.postgresql.Driver";

	// COSTRUTTORE
	private ConnessioneDatabase() throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, nome, password);

		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
			ex.printStackTrace();
		}

	}


	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnessioneDatabase();
		} else if (instance.connection.isClosed()) {
			instance = new ConnessioneDatabase();
		}
		return instance;
	}
}