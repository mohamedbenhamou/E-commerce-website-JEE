package cn.supermarche.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import cn.supermarche.connection.DbCon;
import cn.supermarche.dao.FournisseurDao;
import cn.supermarche.dao.ProduitDao;
import cn.supermarche.model.Fournisseur;

@WebServlet("/ajouterNouverProduit")
public class AjouterNouveauProduitServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out =response.getWriter()){
			boolean ajouter	 =false;
			String nom=request.getParameter("nom");
			String category=request.getParameter("category");
			double prix = Double.parseDouble(request.getParameter("prix"));

			int quantite = Integer.parseInt(request.getParameter("quantite"));
			String fournisseur = request.getParameter("fournisseur");
			ProduitDao produitDao=new ProduitDao(DbCon.getConnection());
			FournisseurDao fournisseurDao = new FournisseurDao(DbCon.getConnection());
			int id =fournisseurDao.getFournisseurId(fournisseur);
			
			if(id>0) {
				ajouter= produitDao.ajouterNouveauProduit(nom, category, prix, quantite, id);
			}
			
			
			if(!ajouter) {
				request.setAttribute("error", "Mon erreur");
			}
			
			response.sendRedirect("gestionProduits.jsp");
			
			
	}catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
