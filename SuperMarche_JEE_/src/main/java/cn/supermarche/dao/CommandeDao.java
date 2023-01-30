package cn.supermarche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.supermarche.model.Commande;
import cn.supermarche.model.Produit;

public class CommandeDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public CommandeDao(Connection con) {
		this.con=con;
		
	}
	
	public List<Commande> getNouveauCommandes(){
		List<Commande> commandes=new ArrayList<Commande>();
		try {
			query = "select * from orders order by orders.o_id desc";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Commande commande = new Commande();
                ProduitDao produitDao = new ProduitDao(this.con);
                int pId = rs.getInt("p_id");
                Produit produit = produitDao.getSingleProduct(pId);
                commande.setCommandeId(rs.getInt("o_id"));
                commande.setId(pId);
                commande.setName(produit.getName());
                commande.setCategorie(produit.getCategorie());
                commande.setPrix(produit.getPrix()*rs.getInt("o_quantity"));
                commande.setQuantite(rs.getInt("o_quantity"));
                commande.setStatus(rs.getString("STATUS"));
                commande.setDate(rs.getString("o_date"));
                commandes.add(commande);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return commandes;
	}
	
	public boolean insererCommande(Commande commande) {
		boolean resultat=false;
		try{
			query="insert into orders (p_id,u_id,o_quantity,STATUS,o_date) values(?,?,?,?,?)";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,commande.getId());
			pst.setInt(2, commande.getUid());
			pst.setInt(3, commande.getQuantite());
			pst.setString(4, commande.getStatus());
			pst.setString(5,commande.getDate());
			pst.executeUpdate();
			StatistiqueDao statistiqueDao = new StatistiqueDao(con);
			statistiqueDao.incAchatStatisique();
			
			resultat=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return resultat;
		
	}
	
	public List<Commande> userCommandes(int id) {
        List<Commande> list = new ArrayList<>();
        try {
            query = "select * from orders where u_id=? order by orders.o_id desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Commande commande = new Commande();
                ProduitDao produitDao = new ProduitDao(this.con);
                int pId = rs.getInt("p_id");
                Produit produit = produitDao.getSingleProduct(pId);
                commande.setCommandeId(rs.getInt("o_id"));
                commande.setId(pId);
                commande.setName(produit.getName());
                commande.setCategorie(produit.getCategorie());
                commande.setPrix(produit.getPrix()*rs.getInt("o_quantity"));
                commande.setQuantite(rs.getInt("o_quantity"));
                commande.setStatus(rs.getString("STATUS"));
                commande.setDate(rs.getString("o_date"));
                list.add(commande);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
	
	public void annulercommande (int id) {
       
        try {
            query = "delete from orders where o_id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            StatistiqueDao statistiqueDao = new StatistiqueDao(con);
			statistiqueDao.decAchatStatisique();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
	
	
	public void validerCommande(int id,double prix) {
		
		try {
            query = "UPDATE `orders` SET `STATUS`=? WHERE `o_id` = ?";
            pst = this.con.prepareStatement(query);
            pst.setString(1,"succes");
            pst.setInt(2, id);
            pst.execute();
            
            StatistiqueDao statistiqueDao = new StatistiqueDao(con);
            statistiqueDao.incRevenueStatisique(prix);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
		
		
	}
public void refuserCommande(int id,double prix ) {
		
		try {
            query = "UPDATE `orders` SET `STATUS`=? WHERE `o_id` = ?";
            pst = this.con.prepareStatement(query);
            pst.setString(1,"annuler");
            pst.setInt(2, id);
            pst.execute();
            StatistiqueDao statistiqueDao = new StatistiqueDao(con);
            statistiqueDao.decRevenueStatisique(prix);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
		
		
	}

}

