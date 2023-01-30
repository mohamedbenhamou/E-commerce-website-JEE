package cn.supermarche.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.supermarche.connection.DbCon;
import cn.supermarche.dao.CommandeDao;
import cn.supermarche.model.*;

@WebServlet("/finaliser-achat")
public class FinaliserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			out.println("chekout servlet...");
			SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
			Date date=new Date();
			ArrayList<Panier> list_Panier= (ArrayList<Panier>) request.getSession().getAttribute("list-Panier");
			Utilisateur auth=(Utilisateur) request.getSession().getAttribute("auth");
			
			if(list_Panier!=null&&auth!=null) {
				for(Panier p:list_Panier) {
					Commande commande =new Commande();
					commande.setId(p.getId());
					commande.setUid(p.getId());
					commande.setQuantite(p.getQuantite());
					commande.setDate(formatter.format(date));
					
					CommandeDao commandeDao=new CommandeDao(DbCon.getConnection());
					boolean resultat=commandeDao.insererCommande(commande);
					if(!resultat) break;
				}
				list_Panier.clear();
				response.sendRedirect("commandes.jsp");
			}else {
				if(auth==null) response.sendRedirect("login.jsp");
				response.sendRedirect("panier.jsp");
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
