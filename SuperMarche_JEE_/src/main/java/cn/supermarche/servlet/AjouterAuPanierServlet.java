package cn.supermarche.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import cn.supermarche.model.Panier;

@WebServlet("/Ajouter_Au_Panier")
public class AjouterAuPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			ArrayList<Panier> listPanier=new ArrayList<>();
			int id=Integer.parseInt(request.getParameter("id"));
			Panier pn=new Panier();
			pn.setId(id);
			pn.setQuantite(1);
			
			
			HttpSession session=request.getSession();
			ArrayList<Panier> list_Panier=(ArrayList<Panier>) session.getAttribute("list-Panier");
			 
			if(list_Panier==null) {
				listPanier.add(pn);
				session.setAttribute("list-Panier", listPanier);
				response.sendRedirect("index.jsp");
			}else {
				listPanier=list_Panier;
				boolean exist=false;
				for(Panier p:list_Panier) {
					if(p.getId()==id) {
						exist=true;
						out.println("<h3 style='color:crimson;text-align:center'>le produit existe deja dans le panier.<a href='panier.jsp'>Aller Au Panier</a></h3>");
					}
				}
				if(!exist) {
					listPanier.add(pn);
					response.sendRedirect("index.jsp");
				}	
					
						
}
					
				}
	}}

		
	



