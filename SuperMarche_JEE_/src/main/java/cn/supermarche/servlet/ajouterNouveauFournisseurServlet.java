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
import cn.supermarche.dao.FournisseurDao;
import cn.supermarche.dao.ProduitDao;
import cn.supermarche.dao.UserDao;

@WebServlet("/ajouterNouveauFournisseur")
public class ajouterNouveauFournisseurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out =response.getWriter()){
			boolean ajouter	 =false;
			String nom=request.getParameter("nom");
			String email=request.getParameter("email");
			String NumTel = request.getParameter("NumTel");
			String adress = request.getParameter("adress");
			
			FournisseurDao fournisseurDao=new FournisseurDao(DbCon.getConnection());
			
				ajouter= fournisseurDao.ajouterNouveauFournisseur(nom, email, adress, NumTel);

			if(!ajouter) {
				request.setAttribute("error", "Mon erreur");
			}
			
			response.sendRedirect("Fournisseurs.jsp");
			
			
	}catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
