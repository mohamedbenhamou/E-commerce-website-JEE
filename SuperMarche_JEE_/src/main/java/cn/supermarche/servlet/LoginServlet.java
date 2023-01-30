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
import cn.supermarche.dao.UserDao;
import cn.supermarche.model.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out =response.getWriter()){
			String email=request.getParameter("login-email");
			String password=request.getParameter("password");
			UserDao udao=new UserDao(DbCon.getConnection());
			Utilisateur utilisateur=udao.userlogin(email, password);
			if(utilisateur != null) {
				out.print("user login");
				request.getSession().setAttribute("auth", utilisateur);
				if(utilisateur.isAdmin()) {
					response.sendRedirect("adminPageAccueil	.jsp");
				}else {
					response.sendRedirect("index.jsp");
				}
				
				
			}else {
				out.print("user login failed");
				
			}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
}
}
