package cn.supermarche.model;

public class Produit {
	private int id;
	private String name;
	private String categorie;
	private double prix;
	private String image;
	private int quantitePrduit;
	private int fournisseurId;
	
	public Produit() {
	
	}
	public Produit(int id, String name, String categorie, double prix, String image,int quantite) {
		super();
		this.id = id;
		this.name = name;
		this.categorie = categorie;
		this.prix = prix;
		this.image = image;
		this.quantitePrduit=quantite;
	}
	
	
	public int getQuantitePrduit() {
		return quantitePrduit;
	}
	public void setQuantitePrduit(int quantitePrduit) {
		this.quantitePrduit = quantitePrduit;
	}
	public int getFournisseurId() {
		return fournisseurId;
	}
	public void setFournisseurId(int fournisseurId) {
		this.fournisseurId = fournisseurId;
	}
	public int getQuantite() {
		return quantitePrduit;
	}
	public void setQuantite(int quantite) {
		this.quantitePrduit = quantite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", name=" + name + ", categorie=" + categorie + ", prix=" + prix + ", image="
				+ image + "]";
	}
	
	
	
	
	

}
