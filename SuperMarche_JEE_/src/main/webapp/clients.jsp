<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="cn.supermarche.dao.UserDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth",auth);
	UserDao userDao  = new UserDao(DbCon.getConnection());
	utilisateurs = userDao.getAllUtulisateur();
}else{
	response.sendRedirect("index.jsp");
}%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./style.css">
<title>Clients</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
<link rel="stylesheet" href="./style.css">
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<%@include file="includes/footer.jsp" %>
	
	<div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="user">
                    <li>
                    <a href="adminPageAccueil.jsp">	
                            <ion-icon class="adminIcon" name="person-circle-outline"></ion-icon>
                            <ion-icon size="large"></ion-icon>
                        
                    </a>
                </li>
                </div>
            </div>
            
            <div class="details">
                <div class="Orders">
                    

                    <table>
                        <thead>
                            <tr>
                            	<td>Id</td>
                                <td>Nom du client</td>
                                <td>Email</td>
                                <td>Adress</td>
                                <td>Num Tel</td>
                                
                            </tr>
                        </thead>

                        <tbody>
                        
                        <%
						if(!utilisateurs.isEmpty()){
						for(Utilisateur u:utilisateurs){%>
							<tr>
								<td><%= u.getId() %></td>
                                <td><%= u.getName() %></td>
                                <td><%= u.getEmail() %></td>
                                <td><%= u.getAdress() %></td>
                                <td><%= u.getNumTel()%></td>
                                
                                
                                <td><a class="btn  btn-danger" href="supprimerUtilisateur?id=<%=u.getId()%>">Supprimer l'utilisaeur</a></td>
                 	
                            </tr>
                            
                            
                         	<%
                         	
							}
								}
							%>
                        
                        </tbody>
                    </table>
                </div>

               
                
            </div>
            
            </div>
	



</body>
</html>