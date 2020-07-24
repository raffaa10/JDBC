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
		for(Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
	}
	
	/**fait un insert dans la base de compta sur la table fournisseur*/
	@Override
	public List<Fournisseur> extraire() {
		Connection connection = null;
		List<Fournisseur> listeFour = new ArrayList<Fournisseur>();
		
		try {
			connection = getConnection(); // Jeton de permission et d'accées à la base
			/**
			 * Récupérer un Statement = accés aux données à partir de l'objey connection
			 * Récupérer le Resultat de la requete
			 * Ajouter ligne par ligne dans la liste des Fournisseurs
			 */
			// Récupérer un buffer d'échange avec la BDD
			// Un tuyau de communication pour échanger avec la BDD pour faire des requetes
			Statement monCanal = connection.createStatement();
			ResultSet monResultat = 
					monCanal.executeQuery("SELECT * FROM t_fournisseur");
			while(monResultat.next()){
				listeFour.add(new Fournisseur(monResultat.getInt("id"), monResultat.getString("nom")));
			}
			monResultat.close();
			monCanal.close();
			// connection.close();
		} 
		catch (Exception e) {
			System.err.println("Erreur d'éxcution : " + e.getMessage());
		}
		finally {
			try {
				if(connection != null) connection.close();
			}
			catch(SQLException e) {
				System.err.println("Problem de connexion close : " + e.getMessage());
			}
		}
		return listeFour;
	}
	/**fait un update dans la table fournisseur*/
	@Override
	public void insert(Fournisseur fournisseur) {
try {
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				//if(connection != null) connection.close();
			}
			catch(SQLException e) {
				System.err.println("Problem de connexion close : " + e.getMessage());
			}
		}
		return ;

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("database");
		
		try {
			Class.forName(db.getString("db.driver"));
			
			return DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));
		} catch (ClassNotFoundException |SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	} 

}
