<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="cn.supermarche.dao.CommandeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
List<Commande> commandes = null;
if(auth!=null){
	request.setAttribute("auth",auth);
	CommandeDao commandeDao  = new CommandeDao(DbCon.getConnection());
	commandes = commandeDao.getNouveauCommandes();
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
<title>Commandes</title>
</head>
<body>

<div class = "sedebar"><%@include file="/includes/navbar.jsp"%></div>


<div class="main">

<div class="details">
                <div class="Orders">
	
		<div class="table">
		
		
		
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<td>Status</td>
					<th scope="col"></th>
					<th scope="col"></th>
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
									<td><span class="status Attente">en attente</span></td>
									
								<%}else if(c.getStatus().equals("succes")){%>
									<td> <span class="status succes">Succes</span></td>
							
								<%}else if(c.getStatus().equals("annuler")) {%>
							
									<td> <span class="status annuler">Annuler</span></td>
							
						<%}
						
                                
						
						%>
						<%
						if(c.getStatus().equals("annuler")){%>
						
							<td><a class="btn btn-sm btn-primary" href="ValiderCommande?id=<%=c.getCommandeId()%>&prix=<%=c.getPrix()%>">Accepter la commande</a></td>
						<% 
						}else{%>
							<td><a class="btn btn-sm btn-danger" href="RefuserCommande?id=<%=c.getCommandeId()%>&prix=<%=c.getPrix()%>">Refuser la commande</a></td>
							
						<%}
						
						%>
						
						
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
	</div>
	
	<%@include file="/includes/footer.jsp"%>
</body>
</html>