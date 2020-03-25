package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.ArrayList;
import java.util.Iterator;

public class TestSelect {
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
		
		//fait un SELECT dans la base de compta sur la table fournisseur
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM fournisseur");
		ArrayList<Fournisseur> listeFournisseurs = new ArrayList<Fournisseur>();
		while (resultSet.next()) {
			listeFournisseurs.add(new Fournisseur(resultSet.getInt("id"),resultSet.getString("nom")));
		}
		
		//affiche les differentes infos de la table fournisseur
		Iterator<Fournisseur> iterator = listeFournisseurs.iterator();
		while (iterator.hasNext()) {
			Fournisseur fournisseur = iterator.next();
			System.out.println(fournisseur.toString());
		}

		// liberation des ressources
		connection.close();
	}
}
