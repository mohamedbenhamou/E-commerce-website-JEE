package cn.supermarche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.supermarche.model.Produit;
import cn.supermarche.model.Statistique;
import cn.supermarche.model.Utilisateur;

public class StatistiqueDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public StatistiqueDao(Connection con) {
		super();
		this.con = con;
	}
	
	
	public void getStatistique() {
	        try {
	            query = "select * from statistiques where id=1 ";

	            pst = this.con.prepareStatement(query);
	            ResultSet rs = pst.executeQuery();

	            if(rs.next()) {

	            	Statistique.setAchats(String.valueOf(rs.getInt("achat")));
	            	Statistique.setFournisseurs(String.valueOf(rs.getInt("fournisseurs")));
	            	Statistique.setRevenues(String.valueOf(rs.getInt("revenus")));
	            	Statistique.setClients(String.valueOf(rs.getInt("clients")));
	            	
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	    
	    }
	
	
	
	public void incClientStatisique() {
		
		try {
			query = "UPDATE `statistiques` SET `clients`=`clients`+1 WHERE `id`=1";
			pst=this.con.prepareStatement(query);
			
			if(pst.executeUpdate()>0) {

	        }
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
public void decClientStatisique() {
		
		try {
			query = "UPDATE `statistiques` SET `clients`=`clients`-1 WHERE `id`=1";
			pst=this.con.prepareStatement(query);

			if(pst.executeUpdate()>0) {

	        }
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

public void incAchatStatisique() {
	
	try {
		query = "UPDATE `statistiques` SET `achat`=`achat`+1 WHERE `id`=1";
		pst=this.con.prepareStatement(query); 
		
		if(pst.executeUpdate()>0) {

        }
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}

public void decAchatStatisique() {
	
	try {
		query = "UPDATE `statistiques` SET `achat`=`achat`-1 WHERE `id`=1";
		pst=this.con.prepareStatement(query); 
		
		if(pst.executeUpdate()>0) {

        }
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}

public void incRevenueStatisique(double prix) {
	
	try {
		query = "UPDATE `statistiques` SET `revenus`=`revenus`+? WHERE `id`=1";
		pst=this.con.prepareStatement(query); 
		pst.setDouble(1, prix);
		if(pst.executeUpdate()>0) {

        }
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
public void decRevenueStatisique(double prix) {
	
	try {
		query = "UPDATE `statistiques` SET `revenus`=`revenus`-? WHERE `id`=1";
		pst=this.con.prepareStatement(query); 
		pst.setDouble(1, prix);
		if(pst.executeUpdate()>0) {

        }
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}

public void incfournisseurStatisique() {
		
	try {
		query = "UPDATE `statistiques` SET `fournisseurs`=`fournisseurs`+1 WHERE `id`=1";
		pst=this.con.prepareStatement(query); 
		if(pst.executeUpdate()>0) {

        }
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
public void decfournisseurStatisique() {
	
	try {
		query = "UPDATE `statistiques` SET `fournisseurs`=`fournisseurs`-1 WHERE `id`=1";
		pst=this.con.prepareStatement(query); 

		if(pst.executeUpdate()>0) {

        }
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
	

}
