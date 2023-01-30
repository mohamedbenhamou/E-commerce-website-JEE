<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="cn.supermarche.dao.ProduitDao"%>
<%Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth",auth);
}
ProduitDao pd=new ProduitDao(DbCon.getConnection());
List<Produit> produits=pd.getAllProduits();
ArrayList<Panier> list_Panier=(ArrayList<Panier>) session.getAttribute("list-Panier");
if(list_Panier!=null){
	request.setAttribute("list_Panier",list_Panier);
}

%>

<!DOCTYPE html>
<html>
<head>
<title>SuperMarche</title>
<%@include file="includes/head.jsp"%>

<style type="text/css">

body{

background-image:url("abtal.jpeg")


}

.card{

transition: transform .1s;

}

.card:hover{

transform: scale(1.1);

}

.card-header{

color:white;

font-size:30px;

text-align:center;

}

</style>

<style type="text/css">
body{
background-image:url("abtal.jpeg")
 
}
</style>


</head>
<body>



	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">Tous Les Produits</div>
		<div class="row">
		<%
		if(!produits.isEmpty()){
			for(Produit p:produits){%>
				<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img src="images/<%= p.getImage() %>.jpeg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%= p.getName() %></h5>
						<h6 class="prix">Prix:<%= p.getPrix() %> Dh</h6>
						<h6 class="categorie">categorie:<%= p.getCategorie() %></h6>
						<div class="mt-3 d-flex justify-content-between">
						<a href="Ajouter_Au_Panier?id=<%= p.getId() %>" class="btn btn-primary btn-dark">Ajouter</a>
						<a href="acheter_maintenant?quantite=1&id=<%= p.getId() %>" class="btn btn-primary">Acheter</a>
						
						</div>
					
						
					</div>
				</div>
			</div>
				
			<%}
		}
		%>
			
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>



</body>
</html>