package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	public List<Fournisseur> extraire() {
		loadBdd();
		// fait un SELECT dans la base de compta sur la table fournisseur
		ArrayList<Fournisseur> listeFournisseurs = new ArrayList<Fournisseur>();
		try {
			resultSet = statement.executeQuery("SELECT * FROM fournisseur");
			while (resultSet.next()) {
				listeFournisseurs.add(new Fournisseur(resultSet.getInt("id"), resultSet.getString("nom")));
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
		closeBdd();
		
		return listeFournisseurs;
	}

	/**fait un insert dans la base de compta sur la table fournisseur*/
	public void insert(Fournisseur fournisseur) {
		loadBdd();
		try {
			statement.executeUpdate("INSERT INTO fournisseur (id, nom) VALUES ("+fournisseur.getId()+", '"+fournisseur.getNom()+"') ");
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
		closeBdd();

	}

	/**
	 * fait un update dans la table fournisseur en changeant le nom ancienNom par nouveauNom
	 */
	public int update(String ancienNom, String nouveauNom) {
		loadBdd();
		int nombreLignes=0;
		try {
			nombreLignes=statement.executeUpdate("UPDATE fournisseur SET nom='"+nouveauNom+"' WHERE nom='"+ancienNom+"'");

		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
		closeBdd();
		return nombreLignes;
	}

	/**
	 *supprime le fournisseur specifie dans la table fournisseur
	 */
	public boolean delete(Fournisseur fournisseur) {
		loadBdd();
		int nombreLignes=0;
		try {
			nombreLignes=statement.executeUpdate("DELETE FROM fournisseur where id="+fournisseur.getId());

		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
		closeBdd();
		if (nombreLignes==0)
			return false;
		else
			return true;
	}

	public void loadBdd() {
		// recupere le fichier properties
		ResourceBundle db = ResourceBundle.getBundle("database");

		// enregistre le pilote
		try {
			Class.forName(db.getString("db.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// creer la connection
		try {
			connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// affiche la connexion
		boolean valid = false;
		try {
			valid = connection.isValid(500);
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					e.printStackTrace();
				}
			}
		} 
		if (valid)
			System.out.println("La connection est ok");
		else
			System.out.println("Il y a une erreur de connection");
		
		//creer le statement
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 

	}

	void closeBdd() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
