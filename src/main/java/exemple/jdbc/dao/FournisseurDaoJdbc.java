package exemple.jdbc.dao;

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

	public static void main(String[] a) {
		FournisseurDaoJdbc ofo = new FournisseurDaoJdbc();
		List<Fournisseur> listeFour = ofo.extraire();
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
		// ofo.insert(new Fournisseur(9, "Lesieurs"));
		ofo.update("Lesieurs", "Leclerc");
		listeFour = ofo.extraire();
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
		
		if(ofo.delete(new Fournisseur(9,"Leclerc"))) {
			System.out.println("Fournisseur supprimé !");
		}
		listeFour = ofo.extraire();
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
	}

	/** fait un insert dans la base de compta sur la table fournisseur */
	@Override
	public List<Fournisseur> extraire() {
		Connection connection = null;
		List<Fournisseur> listeFour = new ArrayList<Fournisseur>();

		try {
			connection = getConnection(); // Jeton de permission et d'accées à la base
			/**
			 * Récupérer un Statement = accés aux données à partir de l'objey connection
			 * Récupérer le Resultat de la requete Ajouter ligne par ligne dans la liste des
			 * Fournisseurs
			 */
			// Récupérer un buffer d'échange avec la BDD
			// Un tuyau de communication pour échanger avec la BDD pour faire des requetes
			Statement monCanal = connection.createStatement();
			ResultSet monResultat = monCanal.executeQuery("SELECT * FROM t_fournisseur");
			while (monResultat.next()) {
				listeFour.add(new Fournisseur(monResultat.getInt("id"), monResultat.getString("nom")));
			}
			monResultat.close();
			monCanal.close();
			// connection.close();
		} catch (Exception e) {
			System.err.println("Erreur d'éxcution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Problem de connexion close : " + e.getMessage());
			}
		}
		return listeFour;
	}

	/** fait un insert dans la base de compta sur la table t_fournisseur */
	@Override
	public void insert(Fournisseur fournisseur) {
		Connection connection = null;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			int nb = monCanal.executeUpdate("INSERT INTO t_fournisseur (id,nom) values (" + fournisseur.getId() + ",'"
					+ fournisseur.getNom() + "');");

			if (nb == 1) {
				System.out.println("Fournisseur ajouté !");
			}
		} catch (Exception e) {
			System.out.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Problem de connexion close : " + e.getMessage());
			}
		}

	}

	/** fait un update dans la table t_fournisseur */
	@Override
	public int update(String ancienNom, String nouveauNom) {

		Connection connection = null;
		int nb = 0;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			nb = monCanal.executeUpdate(
					"UPDATE t_fournisseur SET nom = '" + nouveauNom + "' WHERE nom = '" + ancienNom + "';");

			monCanal.close();

		} catch (Exception e) {
			System.out.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Problem de connexion close : " + e.getMessage());
			}
		}
		return nb;
	}

	/**
	 *suppression le fournisseur specifie dans la table t-fournisseur
	 */
	@Override
	public boolean delete(Fournisseur fournisseur) {
		
		Connection connection = null;
		boolean nb = false;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			nb = monCanal.executeUpdate(
					"delete from t_fournisseur WHERE id="+fournisseur.getId()+";") == 1;
			monCanal.close();
		}
		catch(Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}
		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Problem de connexion close : " + e.getMessage());
			}
		}
		return nb;

	}

	
	
	
	
	
	public Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("database");

		try {
			Class.forName(db.getString("db.driver"));

			return DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
