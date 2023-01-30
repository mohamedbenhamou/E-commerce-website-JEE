<%@page import="cn.supermarche.dao.FournisseurDao"%>
<%@page import="cn.supermarche.dao.ProduitDao"%>
<%@page import="cn.supermarche.connection.DbCon"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%
List<Produit> produits=new ArrayList<Produit>();
List<Fournisseur> fournisseurs=new ArrayList<Fournisseur>();
Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth",auth);
	ProduitDao commandeDao  = new ProduitDao(DbCon.getConnection());
	produits = commandeDao.getAllProduits();
	
	FournisseurDao fournisseurDao = new FournisseurDao(DbCon.getConnection());
	fournisseurs = fournisseurDao.getAllFournisseurs();
	
}
else{
	response.sendRedirect("index.jsp");
}

%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>

<link rel="stylesheet" href="./style.css">

<title>Produits</title>


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
                                <td>Nom de produit</td>
                                <td>Categorie</td>
                                <td>Fournisseur</td>
                                <td>Prix</td>
                                <td>Quantite</td>
                                <td>Supprimer le produit</td>
                                
                            </tr>
                        </thead>

                        <tbody>
                        
                        <%
						if(!produits.isEmpty()){
						for(Produit p:produits){%>
							<tr>
                                <td><%= p.getName() %></td>
                                <td><%= p.getCategorie() %></td>
                                <td><%= p.getFournisseurId() %></td>
                                <td><%= p.getPrix() %></td>
                                
                                <td>
						<form action="ModifierProduit.jsp?id=<%=p.getId()%>" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=p.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre" href="quantiteProduits?action=dec&id=<%=p.getId()%>"><i class="fas fa-minus-square"></i></a>
								<input type="text" name="quantite" class="form-control w-50" value="<%=p.getQuantite()%>" readonly> <a class="btn btn-sm btn-incre" href="quantiteProduits?action=inc&id=<%=p.getId()%>">
									<i class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Modifier</button>
						</form>
					</td>
                                
                                
                                <td><a class="btn btn-danger " href="supprimerProduit?id=<%=p.getId()%>">Supprimer le Produit</a></td>
                 	
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
                <form class="form" role="form" autocomplete="off" id="formLogin" method="POST" action="ajouterNouverProduit">
                    <div class="form-group">
 
                               
                        <label for="uname1">Nom</label>
                        <input type="text" class="form-control rounded-0" name="nom" 
                               required>
                    </div>
                    <div class="form-group">
                        <label for="uname1">category</label>
                        <input type="text" class="form-control rounded-0" name="category" id="uname1" 
                               required>
                    </div>
                    <div class="form-group">
                        <label>prix</label>
                        <input type="number" class="form-control rounded-0"  name="prix" 
                               required pattern="[0-9]{10}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleTextarea">quantite</label>
                        <input type="number"  class="form-control rounded-0" id="exampleTextarea" name="quantite" />
                    </div>
                    <div class="form-group">
                        <label>fournisseur</label>
                        <select  class="form-control rounded-0" name="fournisseur" id="fournisseur">
                        <%
                        if(!fournisseurs.isEmpty()){
                        for(Fournisseur f : fournisseurs){
                        	
                        	%>
                        	<option value="<%=f.getnom()%>"><%=f.getnom()%></option>
                        <% }
                        }
                        %>
</select> 
                     
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