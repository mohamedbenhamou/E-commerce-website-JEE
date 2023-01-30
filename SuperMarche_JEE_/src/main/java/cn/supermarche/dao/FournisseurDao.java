package cn.supermarche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.supermarche.model.*;

public class FournisseurDao {
	
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public FournisseurDao(Connection con) {
		super();
		this.con = con;
	}
	
	
	public List<Fournisseur>getAllFournisseurs(){
		List<Fournisseur> fournisseurs=new ArrayList<Fournisseur>();
		try {
			query="select * from fournisseur";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Fournisseur fournisseur=new Fournisseur();
				fournisseur.setId(rs.getInt("id"));
				fournisseur.setId(rs.getInt("id"));
            	fournisseur.setnom(rs.getString("nom"));
            	fournisseur.setEmail(rs.getString("email"));
            	fournisseur.setAdress(rs.getString("adress"));
            	fournisseur.setNumTel(rs.getString("NumTel"));
				fournisseurs.add(fournisseur);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return fournisseurs;
	}
	
public int getFournisseurId(String name) {
	int id =0;
	List<Fournisseur> fournisseurs = getAllFournisseurs();
	System.out.print(fournisseurs);
	for(Fournisseur f:fournisseurs) {
		if(f.getnom().equals(name)) {
			id=f.getId();
		}
	}
	return id;
	
}
	
	public Fournisseur getSingleFournissuer(int id) {
		 Fournisseur fournisseur = null;
	        try {
	            query = "select * from fournisseur where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	fournisseur = new Fournisseur();
	            	fournisseur.setId(rs.getInt("id"));
	            	fournisseur.setnom(rs.getString("nom"));
	            	fournisseur.setEmail(rs.getString("email"));
	            	fournisseur.setAdress(rs.getString("adress"));
	            	fournisseur.setNumTel(rs.getString("NumTel"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return fournisseur;
	    }
	
	public boolean ajouterNouveauFournisseur(String nom,String email,String adress,String NumTel) {
		boolean ajouter=false;
		
		
		try {
	        query = "INSERT INTO `fournisseur`(`nom`, `email`, `adress`, `NumTel`) VALUES (?,?,?,?)";
	        pst = this.con.prepareStatement(query);
	        pst.setString(1, nom);
	        pst.setString(2, email);
	        pst.setString(3, adress);
	        pst.setString(4, NumTel);
	        if(pst.executeUpdate()>0) {
	        	ajouter=true;
	        	StatistiqueDao statistiqueDao = new StatistiqueDao(con);
	        	statistiqueDao.incfournisseurStatisique();
	        	
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print(e.getMessage());

	}
		return ajouter;
	}
	
	public void SupprimerFournisseur(int id) {
		 
		 try {
	           query = "DELETE FROM `fournisseur` WHERE `id`=?";
	           pst = this.con.prepareStatement(query);
	           pst.setInt(1, id);
	           pst.execute();
	           StatistiqueDao statistiqueDao = new StatistiqueDao(con);
	           statistiqueDao.decfournisseurStatisique();
	       } catch (SQLException e) {
	           e.printStackTrace();
	           System.out.print(e.getMessage());
	       }

	}
	
	

}
