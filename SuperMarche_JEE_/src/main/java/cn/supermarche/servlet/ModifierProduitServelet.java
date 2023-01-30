package cn.supermarche.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import cn.supermarche.connection.DbCon;
import cn.supermarche.dao.ProduitDao;
import cn.supermarche.dao.UserDao;
import cn.supermarche.model.Utilisateur;

@WebServlet("/modifierProduitSelected")
public class ModifierProduitServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("adminPageAccueil.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out =response.getWriter()){
			boolean modifer =false;
			int id = Integer.parseInt(request.getParameter("id"));
			String nom=request.getParameter("nom");
			String category=request.getParameter("category");
			double prix = Double.parseDouble(request.getParameter("prix"));

			int quantite = Integer.parseInt(request.getParameter("quantite"));
			int fournisseur = Integer.parseInt(request.getParameter("founisseur"));
			ProduitDao produitDao=new ProduitDao(DbCon.getConnection());
			modifer= produitDao.ModifierProduit(id, nom, category, prix, quantite, fournisseur);
			
			if(!modifer) {
				request.setAttribute("error", "Mon erreur");
				response.sendRedirect("ModifierProduit.jsp?id="+id);
			}
			else {
				response.sendRedirect("gestionProduits.jsp");
				
			}
			
			
			
	}catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	}

}
