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
@WebServlet("/quantite_inc_dec")

public class QuantiteIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter();){
			String action=request.getParameter("action");
			int id=Integer.parseInt(request.getParameter("id"));
			ArrayList<Panier> list_Panier= (ArrayList<Panier>) request.getSession().getAttribute("list-Panier");
			if(action !=null && id>=1) {
				if(action.equals("inc")) {
					for(Panier p:list_Panier) {
						if(p.getId()==id) {
							int quantite=p.getQuantite();
							quantite++;
							p.setQuantite(quantite);
							response.sendRedirect("panier.jsp");
						}
					}
					
				}
				if(action.equals("dec")) {
					for(Panier p:list_Panier) {
						if(p.getId()==id && p.getQuantite()>1) {
							int quantite=p.getQuantite();
							quantite--;
							p.setQuantite(quantite);
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

