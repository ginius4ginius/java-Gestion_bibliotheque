package modele;

public class Auteur {
	
	private String nom,prenom,nationalite;
	private int num;
	
	public Auteur(String nom, String prenom, String nationalite){
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNationalite() {
		return nationalite;
	}
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public String ToString(){
		return "nom: "+nom+" / prenom: "+prenom+" / nationalite: "+nationalite;	
				}

}
