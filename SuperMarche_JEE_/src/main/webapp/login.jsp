<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
if(auth!=null){
	response.sendRedirect("index.jsp");
}
ArrayList<Panier> list_Panier=(ArrayList<Panier>) session.getAttribute("list-Panier");
if(list_Panier!=null){
	request.setAttribute("list_Panier",list_Panier);
}
%>	
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<%@include file="includes/head.jsp" %>

<style type="text/css">

body{

background-image:url("ananas.jpeg")


}
</style>

</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<%@include file="includes/footer.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Authentification Utilisateur</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Adress Email</label> <input class="form-control"
							type="email" name="login-email"
							placeholder="enter votre adress email" required>
					</div>
					<div class="form-group">
						<label>Mot De Passe</label> <input class="form-control"
							type="password" name="password" placeholder="********"
							required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Se
							Connecter</button>
					</div>

				</form>
			</div>

		</div>
	</div>


</body>
</html>