package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestInsertion {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// recupere le fichier properties
		ResourceBundle db = ResourceBundle.getBundle("database");

		// enregistre le pilote
		Class.forName(db.getString("db.driver"));

		// creer la connection
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
				db.getString("db.pass"));

		// affiche la connexion
		boolean valid = connection.isValid(500);
		if (valid)
			System.out.println("La connection est ok");
		else
			System.out.println("Il y a une erreur de connection");
		
		//fait un INSERT dans la base de compta
		Statement statement = connection.createStatement();
		statement.executeUpdate("INSERT INTO fournisseur (id, nom) VALUES (4, 'La Maison de la Peinture') ");

		// liberation des ressources
		connection.close();
	}
}
