package application;

import modele.Auteur;
import modele.Livre;

public class Test extends Dao {

	public static void main(String[] args) {
		
		Auteur a = new Auteur("Derieu", "Vincent", "Français");
		Auteur b = new Auteur("Semprun","Jorge","Espagnol");
		Livre l = new Livre("2-07-039692-6 ", "javaPourLesNulls", "ginius",2017,a.getNum());
		Livre l2 = new Livre("2-07-039792-6 ", "SQLPourLesNulls", "ginius",2017,a.getNum());
		
		/*
		System.out.println(ajouterAuteur(a));
		
		selectAuteurs();
		
		System.out.println(ajouterLivreDunAuteur(l,a));
		System.out.println(ajouterLivreDunAuteur(l2,a));
		
		
		selectLivresParAuteur(a);
		
		System.out.println(supprimerLivre(l2));
		selectLivresParAuteur(a);
		*/

	}

}
