package TP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnectionJdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = null;

		try {
			// recupere le fichier properties
			ResourceBundle db = ResourceBundle.getBundle("database");

			// enregistre le pilote
			Class.forName(db.getString("db.driver"));

			// creer la connection
			connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));

			// affiche la connexion
			boolean valid = connection.isValid(500);
			if (valid)
				System.out.println("La connection est ok");
			else
				System.out.println("Il y a une erreur de connection");

			// liberation des ressources
			connection.close();
		} catch (

		SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

}
