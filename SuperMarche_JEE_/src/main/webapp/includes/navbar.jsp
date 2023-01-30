
<%@page import="cn.supermarche.model.Utilisateur"%>
<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
	
	<%
				if (auth == null) {
					%>
					<a class="navbar-brand" href="index.jsp">Super Marché</a>
	
	<%}else if(!auth.isAdmin()){ %>
		<a class="navbar-brand" href="index.jsp">Super Marché</a>
		<%} %>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse mx-50" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto mb-2 mb-lg-0">
				
				<%
				if (auth == null) {
					%>
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="panier.jsp">Panier<span class="badge badge-danger px-1">${list_Panier.size()}</span></a></li>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
					
					<%
				} else {
					
						if(auth.isAdmin()){
						%>
						<div class="container">
        <div class="navigation">
            <ul>
                <li>
                    <a href="adminPageAccueil.jsp">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                        <span class="title">Super Marché </span>
                    </a>
                </li>

                <li>
                    <a href="adminPageAccueil.jsp">
                        <span class="icon">
                            <ion-icon name="stats-chart-outline"></ion-icon>
                        </span>
                        <span class="title">Statistique</span>
                    </a>
                </li>
                
                <li>
                    <a href="gestionProduits.jsp">
                        <span class="icon">
                            <ion-icon name="bag-add-outline"></ion-icon>
                        </span>
                        <span class="title">Produits</span>
                    </a>
                </li>
                
                <li>
                    <a href="gestionCommandes.jsp">
                        <span class="icon">
                           <ion-icon name="document-text-outline"></ion-icon>
                        </span>
                        <span class="title">Commandes</span>
                    </a>
                </li>

                <li>
                    <a href="clients.jsp">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                        <span class="title">Clients</span>
                    </a>
                </li>	
                <li>
                    <a href="Fournisseurs.jsp">
                        <span class="icon">
                            <ion-icon name="person-add-outline"></ion-icon>
                        </span>
                        <span class="title">Fournisseur</span>
                    </a>
                </li>

                <li>
                    <a href="log-out">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                        <span class="title">Se deconnecter</span>
                    </a>
                </li>
            </ul>
        </div>
						
						<%
						}else{
						%>	
						
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="index.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="panier.jsp">Panier<span class="badge badge-danger px-1">${list_Panier.size()}</span></a></li>
						<li class="nav-item"><a class="nav-link" href="commandes.jsp">Commandes</a>
			
					<%
						}
						%>
						<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
						<% 	
				}
				%>





			</ul>

		</div>
	</div>
</nav>