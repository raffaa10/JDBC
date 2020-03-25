package TP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnectionJdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//recupere le fichier properties
		ResourceBundle db = ResourceBundle.getBundle("db");
		
		//enregistre le pilote
		Class.forName(db.getString("db.driver"));
		
		//creer la connection
		Connection connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"), db.getString("db.pass"));
		
		//affiche la connexion
		System.out.println("connection :"+connection.toString());
		
		//liberation des ressources
		connection.close();
	}

}
