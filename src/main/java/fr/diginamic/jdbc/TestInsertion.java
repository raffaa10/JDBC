package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestInsertion {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Statement statement = null;
		
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

			// fait un INSERT dans la base de compta
			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO fournisseur (id, nom) VALUES (4, 'La Maison de la Peinture') ");

			// liberation des ressources
			statement.close();
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
				if (statement!=null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
}
