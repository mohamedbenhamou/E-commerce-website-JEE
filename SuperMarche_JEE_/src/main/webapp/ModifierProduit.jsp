<%@page import="cn.supermarche.model.Produit"%>
<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="cn.supermarche.dao.ProduitDao"%>
<%@page import="cn.supermarche.model.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    int id = 0;
    if(request.getParameter("id")==null){
    	response.sendRedirect("adminPageAccueil.jsp");
    }else{
    	id=Integer.parseInt(request.getParameter("id"));
    	
    }
   Produit produit= null; 
	Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
	if(auth!=null && id>0){
		request.setAttribute("auth",auth);
		ProduitDao produitDao  = new ProduitDao(DbCon.getConnection());
		produit = produitDao.getSingleProduct(id);
		if(produit== null){
			response.sendRedirect("adminPageAccueil.jsp");
		}
	}
	else{
		response.sendRedirect("adminPageAccueil.jsp");
	}

%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Produit</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/footer.jsp" %>


<div class="row" style="padding-bottom:60px; padding-top:40px">
    <div class="col-lg-6 col-md-8 mx-auto d-flex flex-column justify-content-center">

        
        <div class="card rounded shadow shadow-sm">
            <div class="card-header">
                <h3 class="mb-0">Inscription</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" autocomplete="off" id="formLogin" method="POST" action="modifierProduitSelected">
                    <div class="form-group">
                    
                    	<label for="uname1">Id</label>
                        <input type="text" class="form-control rounded-0" name="id" value="<%=produit.getId()%>"
                               readonly>
                               
                        <label for="uname1">Nom</label>
                        <input type="text" class="form-control rounded-0" name="nom" value="<%=produit.getName()%>"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="uname1">category</label>
                        <input type="text" class="form-control rounded-0" name="category" id="uname1" value="<%=produit.getCategorie() %>"
                               required>
                    </div>
                    <div class="form-group">
                        <label>prix</label>
                        <input type="number" class="form-control rounded-0"  name="prix" value="<%=produit.getPrix()%>"
                               required pattern="[0-9]{10}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleTextarea">quantite</label>
                        <input type="number"  class="form-control rounded-0" id="exampleTextarea" name="quantite" value="<%=produit.getQuantitePrduit() %>"/>
                    </div>
                    <div class="form-group">
                        <label>fournisseur</label>
                        <input  class="form-control rounded-0" id="pwd1" name="founisseur" value="<%=produit.getFournisseurId() %>"
                               readonly >
                     
                    </div>
                    <a type="button" class="btn btn-danger" href="gestionProduits.jsp">Annuler la modification </a>
                    <button type="submit" class="btn btn-success float-right" id="btnLogin">Modifier</button>
                </form>
                
                <% if(request.getAttribute("error") != null) {%>
     				<div class="error">Une erreur a été rencontrée: <%=request.getAttribute("error")%></div>
 <%}%>
            </div>
            <!--/card-block-->
        </div>
        <!-- /form card login -->

    </div>

</div>



</body>
</html>