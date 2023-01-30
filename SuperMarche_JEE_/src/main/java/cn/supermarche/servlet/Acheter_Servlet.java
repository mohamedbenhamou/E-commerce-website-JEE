package cn.supermarche.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import cn.supermarche.connection.DbCon;
import cn.supermarche.dao.CommandeDao;
import cn.supermarche.model.*;

@WebServlet("/acheter_maintenant")
public class Acheter_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			
			
			SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
			Date date=new Date();
			Utilisateur auth=(Utilisateur) request.getSession().getAttribute("auth");
			if(auth!=null) {
				String produitId=request.getParameter("id");
				int produitQuantite=Integer.parseInt(request.getParameter("quantite"));
				if(produitQuantite<=0) {
					produitQuantite=1;
				}
				Commande commande=new Commande();
				commande.setId(Integer.parseInt(produitId));
				commande.setUid(auth.getId());
				commande.setQuantite(produitQuantite);
				commande.setDate(formatter.format(date));
				CommandeDao commandeDao=new CommandeDao(DbCon.getConnection());
				boolean resultat =commandeDao.insererCommande(commande);
					
				if(resultat) {
					
					ArrayList<Panier> list_Panier= (ArrayList<Panier>) request.getSession().getAttribute("list-Panier");
					if(list_Panier!=null) {
						for(Panier p:list_Panier) {
							if(p.getId()==Integer.parseInt(produitId)) {
								list_Panier.remove(list_Panier.indexOf(p));
								break;
							}
							
						}
						
					}
					response.sendRedirect("commandes.jsp");
				}else {
					out.println("order failed");
				}
				
			}else {
				response.sendRedirect("login.jsp");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
