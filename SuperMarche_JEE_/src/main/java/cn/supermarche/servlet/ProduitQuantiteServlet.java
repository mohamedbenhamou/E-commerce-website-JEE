package cn.supermarche.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.supermarche.connection.DbCon;
import cn.supermarche.dao.CommandeDao;
import cn.supermarche.dao.ProduitDao;
import cn.supermarche.model.Panier;
import cn.supermarche.model.Produit;

@WebServlet("/quantiteProduits")


public class ProduitQuantiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter();){
			String action=request.getParameter("action");
			int id=Integer.parseInt(request.getParameter("id"));
			List<Produit> produits=new ArrayList<Produit>();
			ProduitDao commandeDao = null;
			try {
				commandeDao = new ProduitDao(DbCon.getConnection());
				produits = commandeDao.getAllProduits();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
			if(action !=null && id>=1) {
				if(action.equals("inc")) {
					for(Produit p:produits) {
						System.out.println(p.getId());
						if(p.getId()==id) {
							try{
								commandeDao.IncQuantiteProduit(id);
							}catch(Exception e) {
								e.printStackTrace();
							}
							
							break;
						}
					}
					response.sendRedirect("gestionProduits.jsp");
				}
				if(action.equals("dec")) {
					for(Produit p:produits) {
						if(p.getId()==id && p.getQuantite()>1) {
							try{
								commandeDao.IncQuantiteProduit(id);
							}catch(Exception e) {
								e.printStackTrace();
							}
							break;
							
						}
					}
					response.sendRedirect("gestionProduits.jsp");
					
				}
				
			}else {
				response.sendRedirect("gestionProduits.jsp"); 
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
