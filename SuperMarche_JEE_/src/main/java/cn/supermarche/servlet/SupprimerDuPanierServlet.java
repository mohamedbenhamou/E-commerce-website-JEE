package cn.supermarche.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import cn.supermarche.model.Panier;

@WebServlet("/supprimer_produit_du_panier")
public class SupprimerDuPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			String id=request.getParameter("id");
			if(id!=null) {
				ArrayList<Panier> list_Panier= (ArrayList<Panier>) request.getSession().getAttribute("list-Panier");
				if(list_Panier!=null) {
					for(Panier p:list_Panier) {
						if(p.getId()==Integer.parseInt(id)) {
							list_Panier.remove(list_Panier.indexOf(p));
							break;
						}
						
					}
					response.sendRedirect("panier.jsp");
					
				}
				
			}else {
				response.sendRedirect("panier.jsp");
			}
			
		}
	}

}
