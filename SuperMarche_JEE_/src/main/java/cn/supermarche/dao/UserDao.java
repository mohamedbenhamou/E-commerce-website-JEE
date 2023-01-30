package cn.supermarche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.supermarche.model.*;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public UserDao(Connection con, String query, PreparedStatement pst, ResultSet rs) {
		this.con = con;
		this.query = query;
		this.pst = pst;
		this.rs = rs;
	}
	public UserDao(Connection con) {
		this.con=con;
		
	}
	
	
	public List<Utilisateur> getNouveauClients(){
		List<Utilisateur> clients=new ArrayList<Utilisateur>();
		try {
			query = "select * from `users` WHERE `IS_ADMIN`=0 order by id desc";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Utilisateur user=new Utilisateur();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setAdress(rs.getString("ADRESS"));
				user.setNumTel(rs.getString("NUM_TEL"));
				user.setPassword("*****");
				user.setAdmin(false);
				clients.add(user);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clients;
	}
	
	
	public List<Utilisateur>getAllUtulisateur(){
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		try {
			query="SELECT * FROM `users` WHERE `IS_ADMIN`=0";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Utilisateur user=new Utilisateur();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setAdress(rs.getString("ADRESS"));
				user.setNumTel(rs.getString("NUM_TEL"));
				user.setPassword("*****");
				user.setAdmin(false);
				utilisateurs.add(user);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return utilisateurs;
	}
	
	
	
	public Utilisateur userlogin(String email,String password) {
		Utilisateur user=null;
		try {
			query="select * from users where email=? and password=?";
			pst=this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2,password);
			rs=pst.executeQuery();
			if(rs.next()) {
				user =new Utilisateur();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				
				if(rs.getBoolean("IS_ADMIN")) {
					user.setAdmin(true);
				}
				else {
					user.setAdress(rs.getString("ADRESS"));
					user.setNumTel(rs.getString("NUM_TEL"));
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		return  user;
	}
	
	
	public Utilisateur userRegister(String nomComplet,String email,String numTelephone,String adresse,String password) {
		Utilisateur user=null;
		
		try {
			query="INSERT INTO `users`(`name`, `email`, `password`, `ADRESS`, `NUM_TEL`, `IS_ADMIN`) VALUES (?,?,?,?,?,'0')";
			pst=this.con.prepareStatement(query);
			pst.setString(1, nomComplet);
			pst.setString(2, email);
			pst.setString(3, password);
			pst.setString(4, adresse);
			pst.setString(5,numTelephone);
			if(pst.executeUpdate()>0) {
				user =new Utilisateur();
				user.setName(nomComplet);
				user.setEmail(email);
				user.setPassword(password);
				user.setAdress(adresse);
				user.setNumTel(numTelephone);
				user.setAdmin(false);
				
				StatistiqueDao statistiqueDao = new StatistiqueDao(con);
				statistiqueDao.incClientStatisique();
				
				
			}
		//	resp.sendRedirect(req.getContextPath()+"/login");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		
		
		return  user;
	}
	
	
	public void SupprimerProduit(int id) {
		 
		 try {
	           query = "DELETE FROM `users` WHERE `id`=?";
	           pst = this.con.prepareStatement(query);
	           pst.setInt(1, id);
	           pst.execute();
	           StatistiqueDao statistiqueDao = new StatistiqueDao(con);
	           statistiqueDao.decClientStatisique();
	       } catch (SQLException e) {
	           e.printStackTrace();
	           System.out.print(e.getMessage());
	       }

	}

}
