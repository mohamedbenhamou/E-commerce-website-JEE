<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*"%>
<%@page import="cn.supermarche.dao.ProduitDao"%>
<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
Utilisateur auth = (Utilisateur) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ArrayList<Panier> list_Panier = (ArrayList<Panier>) session.getAttribute("list-Panier");
List<Panier> panierproduit = null;
if (list_Panier != null) {
	ProduitDao pDao = new ProduitDao(DbCon.getConnection());
	panierproduit = pDao.getProduitsPanier(list_Panier);
	double total = pDao.getPrixTotalPanier(list_Panier);
	request.setAttribute("list_Panier", list_Panier);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Panier</title>
<%@include file="includes/head.jsp"%>
<style type="text/css">
.table tbody td {
	vartical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>

</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Prix Total:${ (total>0)?total:0 }Dh</h3>
			<a class="mx-3 btn btn-primary" href="finaliser-achat">Payer Maintenant</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Nom De Produit</th>
					<th scope="col">Categorie</th>
					<th scope="col">Prix</th>
					<th scope="col">Acheter Maintenant</th>	
					<th scope="col">Annuler</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (list_Panier != null) {
					for (Panier c : panierproduit) {
				%>
				<tr>

					<td><%=c.getName()%></td>
					<td><%=c.getCategorie()%></td>
					<td><%=c.getPrix()%></td>
					<td>
						<form action="acheter_maintenant" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre"
									href="quantite_inc_dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a><input type="text"
									name="quantite" class="form-control w-50"
									value="<%=c.getQuantite()%>" readonly> <a
									class="btn btn-sm btn-incre"
									href="quantite_inc_dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Acheter</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="supprimer_produit_du_panier?id=<%= c.getId()%>">Retirer</a></td>


				</tr>

				<%
				}
				}
				%>


			</tbody>
		</table>
	</div>

	<%@include file="includes/footer.jsp"%>


</body>
</html>