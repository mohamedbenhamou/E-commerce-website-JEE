package cn.supermarche.model;

public class Fournisseur {

	private int id;
	private String nom;
	private String email;
	private String adress ;
	private String NumTel;
	
	
	
	public Fournisseur() {
		super();
	}
	public Fournisseur(int id, String nom, String email, String adress, String numTel) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.adress = adress;
		NumTel = numTel;
	}
	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + ", email=" + email + ", adress=" + adress + ", NumTel="
				+ NumTel + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getnom() {
		return nom;
	}
	public void setnom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getNumTel() {
		return NumTel;
	}
	public void setNumTel(String numTel) {
		NumTel = numTel;
	}
	
	
	
	
}
