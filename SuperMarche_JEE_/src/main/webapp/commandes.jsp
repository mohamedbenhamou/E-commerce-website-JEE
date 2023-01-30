<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="cn.supermarche.dao.CommandeDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.*"%>
	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	Utilisateur auth = (Utilisateur) request.getSession().getAttribute("auth");
	List<Commande> commandes = null;
	if (auth != null) {
		request.setAttribute("auth",auth);
	    CommandeDao commandeDao  = new CommandeDao(DbCon.getConnection());
		commandes = commandeDao.userCommandes(auth.getId());
	}else{
		response.sendRedirect("login.jsp");
	}
	ArrayList<Panier> list_Panier=(ArrayList<Panier>) session.getAttribute("list-Panier");
	if(list_Panier!=null){
		request.setAttribute("list_Panier",list_Panier);
	}
	
	%>
<!DOCTYPE html>
<html>
<head>	
<%@include file="/includes/head.jsp"%>
<title>E-Commerce Cart</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Status</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(commandes != null){
				for(Commande c: commandes){%>
					<tr>
						<td><%=c.getDate() %></td>
						<td><%=c.getName() %></td>
						<td><%=c.getCategorie() %></td>
						<td><%=c.getQuantite() %></td>
						<td><%=dcf.format(c.getPrix()) %></td>
						
						<%if(c.getStatus().equals("en attente")){%>
							<td class="badge badge-secondary">En attente</td>
							<td><a class="btn btn-sm btn-danger" href="SupprimerCommande?id=<%=c.getCommandeId()%>">Annuler la commande</a></td>
						<%}else if(c.getStatus().equals("succes")){%>
							<td class="badge badge-success">Succés</td>
							
						<%}else if(c.getStatus().equals("annuler")) {%>
							
							<td class="badge badge-danger">Annuler</td>
							
						<%}
						
						
						
						%>
						
					</tr>
				<%}
			}
			%>
			
			</tbody>
		</table>
	</div>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>