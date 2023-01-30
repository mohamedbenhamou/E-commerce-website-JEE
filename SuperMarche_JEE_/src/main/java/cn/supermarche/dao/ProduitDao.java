package cn.supermarche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.supermarche.model.*;

public class ProduitDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProduitDao(Connection con) {
		this.con = con;
	}
	
public List<Produit> getNouveauProduits(){
		int nbrProduits=0;
		List<Produit> produits=new ArrayList<Produit>();
		try {
			query="select * from products ORDER BY id DESC";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next() && nbrProduits<=8) {
				Produit row=new Produit();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setPrix(rs.getDouble("price"));
				row.setCategorie(rs.getString("category"));
				row.setQuantite(rs.getInt("quantite"));
				row.setFournisseurId(rs.getInt("fournisseur_id"));	
				row.setImage(rs.getString("image"));
				produits.add(row);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return produits;
	}
	
public List<Produit>getAllProduits(){
		List<Produit> produits=new ArrayList<Produit>();
		try {
			query="select * from products";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Produit row=new Produit();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setPrix(rs.getDouble("price"));
				row.setCategorie(rs.getString("category"));
				row.setQuantite(rs.getInt("quantite"));
				row.setFournisseurId(rs.getInt("fournisseur_id"));
				row.setImage(rs.getString("image"));
				produits.add(row);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return produits;
	}
	
public Produit getSingleProduct(int id) {
		 Produit produit = null;
	        try {
	            query = "select * from products where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	produit = new Produit();
	                produit.setId(rs.getInt("id"));
	                produit.setName(rs.getString("name"));
	                produit.setCategorie(rs.getString("category"));
	                produit.setPrix(rs.getDouble("price"));
	                produit.setQuantite(rs.getInt("quantite"));
	                produit.setFournisseurId(rs.getInt("fournisseur_id"));
	                produit.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return produit;
	    }
	
	
public List<Panier> getProduitsPanier(ArrayList<Panier> listPanier){
		 List<Panier> produits =new ArrayList<Panier>();
		 
		 try {
			 if(listPanier.size()>0) {
				 for(Panier elem:listPanier) {
					 query="select * from products where id=?";
					 pst=this.con.prepareStatement(query);
					 pst.setInt(1, elem.getId());
					 rs=pst.executeQuery();
					 while(rs.next()) {
						 Produit row=new Panier();
						 row.setId(rs.getInt("id"));
						 row.setCategorie(rs.getString("category"));
						 row.setName(rs.getString("name"));
						 row.setPrix(rs.getDouble("price")*elem.getQuantite());
						 row.setQuantite(rs.getInt("fournisseur_id"));
						 row.setImage(rs.getString("image"));
						 ((Panier) row).setQuantite(elem.getQuantite());
						 produits.add((Panier) row);
					 }
					
				 }
				 
			 }
		 }
		 catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
		return produits;
		 
		 
	 }
public double getPrixTotalPanier(ArrayList<Panier> listPanier) {
		 double sum=0;
		 try {
			 if(listPanier.size()>0) {
				 for(Panier elem:listPanier) {
					 query="select price from products where id=?";
					 pst=this.con.prepareStatement(query);
					 pst.setInt(1, elem.getId());
					 rs=pst.executeQuery();
					 while(rs.next()) {
						 sum+=rs.getDouble("price")*elem.getQuantite();
						 
					 }
				 }
			 }
			 
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
		 return sum;
	 }
	 
public void IncQuantiteProduit(int id) {
		 
		 try {
	            query = "UPDATE `products` SET `quantite`=`quantite`+1 WHERE `id`=?";
	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            pst.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.print(e.getMessage());
	        }

	 }
	 
public void DecQuantiteProduit(int id) {
		 
		 try {
	            query = "UPDATE `products` SET `quantite`=`quantite`-1 WHERE `id`=?";
	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            pst.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.print(e.getMessage());
	        }

	 }

public void SupprimerProduit(int id) {
	 
	 try {
           query = "DELETE FROM `products` WHERE `id`=?";
           pst = this.con.prepareStatement(query);
           pst.setInt(1, id);
           pst.execute();
       } catch (SQLException e) {
           e.printStackTrace();
           System.out.print(e.getMessage());
       }

}
	 
	 
public boolean ModifierProduit(int id,String name,String category,double prix,int quantite,int fournisseurId ) {
	boolean modifier=false;
	try {
        query = "UPDATE `products` SET `name`=?,`category`=?,`price`=?,`quantite`=?,`fournisseur_id`=?,`image`='abtal' WHERE `id`=?";
        pst = this.con.prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, category);
        pst.setDouble(3, prix);
        pst.setInt(4, quantite);
        pst.setInt(5, fournisseurId);
        pst.setInt(6, id);
        pst.execute();
        if(pst.executeUpdate()>0) {
        	modifier=true;
        	
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.print(e.getMessage());
    }
	return modifier;
}



public boolean ajouterNouveauProduit(String name,String category,double prix,int quantite,int fournisseurId) {
	boolean ajouter=false;
	
	
	try {
        query = "INSERT INTO `products`(`name`, `category`, `price`, `quantite`, `fournisseur_id`, `image`) VALUES (?,?,?,?,?,'abtal')";
        pst = this.con.prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, category);
        pst.setDouble(3, prix);
        pst.setInt(4, quantite);
        pst.setInt(5, fournisseurId);
        if(pst.executeUpdate()>0) {
        	ajouter=true;
        	
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.print(e.getMessage());

}
	return ajouter;
}
}
