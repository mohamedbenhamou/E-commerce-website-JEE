<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="cn.supermarche.dao.FournisseurDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%
List<Fournisseur> fournisseurs=new ArrayList<Fournisseur>();
Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth",auth);
	FournisseurDao fournisseurDao = new FournisseurDao(DbCon.getConnection());
	fournisseurs = fournisseurDao.getAllFournisseurs();
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
<title>Fournisseurs</title>
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
                                <td>Nom du fournisseur</td>
                                <td>Email</td>
                                <td>Num Tel</td>
                                <td>Adress</td>
                                <td>Supprimer le fournisseur</td>
                                
                            </tr>
                        </thead>

                        <tbody>
                        
                        <%
						if(!fournisseurs.isEmpty()){
						for(Fournisseur f:fournisseurs){%>
							<tr>
                                <td><%= f.getnom() %></td>
                                <td><%= f.getEmail() %></td>
                                <td><%= f.getNumTel() %></td>
                                <td><%= f.getAdress() %></td>
                                
                                
                                <td><a class="btn btn-danger " href="supprimerFournisseur?id=<%=f.getId()%>">Supprimer le fournisseur</a></td>
                 	
                            </tr>
                            
                         	<%
                         	
							}
								}
							%>
                        
                        </tbody>
                    </table>
                </div>

               
                
            </div>
            
            
            
        
        	<div class="card-body">
                <form class="form" role="form" autocomplete="off" id="formLogin" method="POST" action="ajouterNouveauFournisseur">
                    <div class="form-group">
 
                               
                        <label for="uname1">Nom</label>
                        <input type="text" class="form-control rounded-0" name="nom" 
                               required>
                    </div>
                    <div class="form-group">
                        <label for="uname1">Email</label>
                        <input type="text" class="form-control rounded-0" name="email" id="uname1" 
                               required>
                    </div>
                    <div class="form-group">
                        <label>Numero de telephone</label>
                        <input type="number" class="form-control rounded-0"  name="NumTel" 
                               required pattern="[0-9]{10}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleTextarea">Adress</label>
                        <input type="text"  class="form-control rounded-0" id="exampleTextarea" name="adress" />
                    </div>
                    
                    <button type="submit" class="btn btn-success float-right" id="btnLogin">Ajouter</button>
                </form>
                
                <% if(request.getAttribute("error") != null) {%>
     				<div class="error">Une erreur a été rencontrée: <%=request.getAttribute("error")%></div>
 <%}%>
            </div>
        
        </div>
    

</body>
</html>