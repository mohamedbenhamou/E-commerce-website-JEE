<%@page import="java.util.List"%>
<%@page import="cn.supermarche.connection.DbCon"%>
<%@page import="cn.supermarche.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%
List<Utilisateur> clients = new ArrayList<Utilisateur>();
List<Commande> commandes = new ArrayList<Commande>();
Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth",auth);
	CommandeDao commandeDao  = new CommandeDao(DbCon.getConnection());
	commandes = commandeDao.getNouveauCommandes();
	
	UserDao userDao  = new UserDao(DbCon.getConnection());
	clients = userDao.getNouveauClients();
	
	StatistiqueDao statistiqueDao = new StatistiqueDao(DbCon.getConnection());
	statistiqueDao.getStatistique();	
	
}
else{
	response.sendRedirect("index.jsp");
}
%> 
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style.css">
    
    <title>Admin Dashboard</title>
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

           
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers"><%=Statistique.getClients() %></div>
                        <div class="cardName">Client</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="people-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers"><%=Statistique.getAchats() %></div>
                        <div class="cardName">Achat</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cart-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers"><%=Statistique.getFournisseurs() %></div>
                        <div class="cardName">Fournisseur</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="person-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers"><%=Statistique.getRevenues()%> Dh</div>
                        <div class="cardName">Revenues</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cash-outline"></ion-icon>
                    </div>
                </div>
            </div>

           
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Recent Orders</h2>
                        <a href="gestionCommandes.jsp" class="btn">View All</a>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <td>Nom</td>
                                <td>Quantite</td>
                                <td>Categorie</td>
                                <td>Price</td>
                                <td>Status</td>
                                <td>Date</td>
                                
                            </tr>
                        </thead>

                        <tbody>
                        
                        <%
						if(!commandes.isEmpty()){
							int nbrCommande=0;
						for(Commande c:commandes){%>
							<tr>
                                <td><%= c.getName() %></td>
                                <td><%= c.getQuantite() %></td>
                                <td><%= c.getCategorie() %></td>
                                <td><%= c.getPrix() %></td>
                                
                                <%if(c.getStatus().equals("en attente")){%>
									<td><span class="status Attente">en attente</span></td>
									
								<%}else if(c.getStatus().equals("succes")){%>
									<td> <span class="status succes">Succes</span></td>
							
								<%}else if(c.getStatus().equals("annuler")) {%>
							
									<td> <span class="status annuler">Annuler</span></td>
							
						<%}
						
                                
						
						%>
                                
                                <td><%= c.getDate() %></td>
                               
                 	
                            </tr>
                            
                         	<%
                         	nbrCommande++;
                         	if(nbrCommande==8){
                         		break;
                         	}
							}
								}
							%>
                        
                        </tbody>
                    </table>
                </div>

               
                <div class="recentCustomers">
                    <div class="cardHeader">
                        <h2>Recent Customers</h2>
                    </div>

                    <table>
                    
                    <%
						if(!clients.isEmpty()){
							int nbrClients=0;
						for(Utilisateur c:clients){%>
						
						<tr>
                            <td width="60px">
                               <ion-icon name="person-circle-outline"></ion-icon>
	
                            </td>
                            <td>
                                <h4><%= c.getName()%> <br> <span><%=c.getNumTel() %></span></h4>
                            </td>
                        </tr>

                    <%
                         	nbrClients++;
                         	if(nbrClients==8){
                         		break;
                         	}
							}
								}
							%>

                     
                    </table>
                </div>
            </div>
        </div>
    </div>
    
    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>