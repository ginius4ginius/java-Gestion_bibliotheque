package modele;

public class Livre {
	
	private String ISBN, titre, editeur;
	private int annee;
	private int numAuteur;
	private int num;
	
	public Livre(String ISBN, String titre, String editeur, int annee, int numAuteur){
		this.ISBN = ISBN;
		this.titre = titre;
		this.editeur = editeur;
		this.numAuteur = numAuteur;
		this.annee = annee;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitre() {
		return titre;
	}

	public String getEditeur() {
		return editeur;
	}

	public int getAnnee() {
		return annee;
	}

	public int getNumAuteur() {
		return numAuteur;
	}
	
	public int getNum() {
		return num;
	}
	
	public String ToString(){
		return "titre: "+titre+" / editeur: "+editeur+" / annee: "+annee+" / ISBN: "+ISBN;
	}

}
