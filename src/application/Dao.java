package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import modele.Auteur;
import modele.Livre;

public class Dao {

	private static Connection conn = null;
	private static ResultSet rs = null;
	private static Statement stat = null;
	private static Statement stat2 = null;
	public static Vector<String> Auteurs = new Vector<String>();
	public static Vector<String> Livres = new Vector<String>();

	public static void connection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException err) {
			System.err.println("pilote non trouvé..");
			System.err.println(err);
			System.exit(1);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bibliotheque", "root", "");
		} catch (SQLException err2) {
			System.err.println("Connexion impossible..");
			System.err.println(err2);
			System.exit(1);
		}
	}

	public static void fermetureConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqlEx) {
			}
			conn = null;
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlEx) {
			}
			rs = null;
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException sqlEx) {
			}
			stat = null;
		}
		if (stat2 != null) {
			try {
				stat2.close();
			} catch (SQLException sqlEx) {
			}
			stat2 = null;
		}

	}

	

	public static boolean selectAuteurs() {

		boolean resultat = false;

		connection();
		// création de l'objet Statement en référence à l'objet Connection . Cet
		// objet permettra d'executer des requetes SQL
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery("SELECT*FROM auteur");
			while (rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String nationalite = rs.getString("nationalite");
				System.out.println("nom: " + nom + " / prenom: " + prenom + " / nationalite: " + nationalite);
				resultat = true;
			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}

		return resultat;
	}
	
	public static boolean selectAuteurs2() {

		boolean resultat = false;

		connection();
		// création de l'objet Statement en référence à l'objet Connection . Cet
		// objet permettra d'executer des requetes SQL
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery("SELECT*FROM auteur");

			while (rs.next()) {
				String nom = rs.getString("nom");
				//String prenom = rs.getString("prenom");
				//String nationalite = rs.getString("nationalite");
				String identite = nom;
				Auteurs.add(identite);
				resultat = true;
			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}

		return resultat;
	}
	

	public static boolean selectLivresParAuteur(Auteur a) {
		boolean resultat = false;
		connection();
		// création de l'objet Statement en référence à l'objet Connection . Cet
		// objet permettra d'executer des requetes SQL
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(
					"SELECT*FROM livre where numAuteur =(SELECT num FROM auteur where nom='" + a.getNom() + "') ");
			while (rs.next()) {
				String titre = rs.getString("titre");
				String editeur = rs.getString("editeur");
				int annee = rs.getInt("annee");
				String ISBN = rs.getString("ISBN");

				System.out
						.println("titre: " + titre + " / editeur: " + editeur + " / annee: " + annee + "ISBN: " + ISBN);
				resultat = true;
			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}
		return resultat;

	}
	
	public static boolean selectLivresParAuteur2(String nomAuteur) {
		boolean resultat = false;
		connection();
		// création de l'objet Statement en référence à l'objet Connection . Cet
		// objet permettra d'executer des requetes SQL
		try {
			
			stat = conn.createStatement();
			rs = stat.executeQuery(
					"SELECT*FROM livre where numAuteur =(SELECT num FROM auteur where nom='" + nomAuteur + "') ");
			while (rs.next()) {
				String titre = rs.getString("titre");
				Livres.add(titre);
				resultat = true;

			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}
		return resultat;

	}

	public static boolean supprimerLivre(Livre l) {
		boolean resultat = false;
		connection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM livre WHERE titre = '" + l.getTitre() + "'");
			resultat = true;
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}
		return resultat;

	}
	
	public static boolean supprimerLivre2(String livre) {
		boolean resultat = false;
		connection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM livre WHERE titre = '" + livre+ "'");
			resultat = true;
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}
		return resultat;

	}

	public static boolean ajouterLivreDunAuteur(Livre l, Auteur a) {
		boolean resultat = false;
		connection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO livre (ISBN,titre,editeur,annee,numAuteur)" + "VALUES('" + l.getISBN()
					+ "','" + l.getTitre() + "','" + l.getEditeur() + "'," + l.getAnnee() + "," + a.getNum() + ")");

			resultat = true;

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}

		return resultat;

	}

	public static boolean ajouterLivreDunAuteur2() {
		boolean resultat = false;
		connection();
		try {
			String auteur = Interface.choiceAuteur.getSelectedItem().toString();
			stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO livre (ISBN,titre,editeur,annee,numAuteur) VALUES('" + Interface.tfIsbn.getText()+
					"','" +Interface.tfTitre.getText()+
					"','" +Interface.tfEditeur.getText()+
					"','" +Integer.parseInt(Interface.tfAnnee.getText())+
					"',(SELECT num FROM auteur WHERE nom ='" +auteur+"'))");
			Interface.tfIsbn.setText("");
			Interface.tfTitre.setText("");
			Interface.tfEditeur.setText("");
			Interface.tfAnnee.setText("");

			resultat = true;

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}

		return resultat;

	}
	
	public static boolean ajouterAuteur(Auteur a) {
		boolean resultat = false;
		connection();
		try {
			stat = conn.createStatement();
			stat2 = conn.createStatement();
			stat.executeUpdate("INSERT INTO auteur (nom,prenom,nationalite)" + " VALUES('" + a.getNom() + "','"
					+ a.getPrenom() + "','" + a.getNationalite() + "')");
			rs = stat2.executeQuery("SELECT num FROM auteur where nom='" + a.getNom() + "'");
			while (rs.next()) {
				int numero = rs.getInt("num");
				a.setNum(numero);
				resultat = true;
			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}

		return resultat;

	}
	
	public static boolean ajouterAuteur2() {
		boolean resultat = false;
		connection();
		try {
			stat = conn.createStatement();
			stat2 = conn.createStatement();
			stat.executeUpdate("INSERT INTO auteur (nom,prenom,nationalite)" + " VALUES('" + Ajout_Auteur.tfNom.getText() + "','"
					+ Ajout_Auteur.tfPrenom.getText() + "','" + Ajout_Auteur.tfNationalite.getText() + "')");
			rs = stat2.executeQuery("SELECT num FROM auteur where nom='" + Ajout_Auteur.tfNom.getText() + "'");
			
				resultat = true;

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			fermetureConnection();
		}

		return resultat;

	}

}
