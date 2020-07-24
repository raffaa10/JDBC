package exemple.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exemple.jdbc.entity.Article;
import fr.diginamic.jdbc.entites.Fournisseur;


public class ArticleDaoJdbc implements ArticleDao {

	public static void main(String[] a) {
		// Réflichissez à gérer l'ajout d'un Fournisseur dans la classe FournisseurDaoJdbc
		// méthode insert...
		FournisseurDaoJdbc ofo = new FournisseurDaoJdbc();
		List<Fournisseur> listeFour = ofo.extraire();
		
		ArticleDaoJdbc oart = new ArticleDaoJdbc();
		List<Article> listeArti = oart.extraire();
		for (Article ar : listeArti) {
			if(ar.getId_fou() == 1) {
				System.out.println(ar);
			}
			
		}
	}

	@Override
	public List<Article> extraire() {
		Connection connection = null;
		List<Article> listeArti = new ArrayList<Article>();
		
		try {
			connection = getConnection(); // Jeton de permission et d'accées à la base
			
			Statement monCanal = connection.createStatement();
			ResultSet monResultat = 
					monCanal.executeQuery("SELECT * FROM t_article");
			while(monResultat.next()){
				listeArti.add(new Article(monResultat.getString("designation"), 
						monResultat.getInt("id"), 
						monResultat.getDouble("prix"), 
						monResultat.getString("ref"),
						monResultat.getInt("id_fou")));
						
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
		return listeArti;
	}

	@Override
	public void insert(Article article) {
		// TODO Auto-generated method stub

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Article article) {
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
