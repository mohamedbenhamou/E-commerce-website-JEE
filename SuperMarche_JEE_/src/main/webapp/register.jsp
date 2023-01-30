<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.supermarche.model.*" %>
<%@page import="java.util.ArrayList" %>
<%Utilisateur auth=(Utilisateur)request.getSession().getAttribute("auth");%>    
<!DOCTYPE html>
<html>
<head>
<title>Stock</title>
<%@include file="includes/head.jsp" %>

</head>
<body>
<%@include file="includes/navbar.jsp"%>
<%@include file="includes/footer.jsp" %>

<div class="row" style="padding-bottom:60px; padding-top:40px">
    <div class="col-lg-6 col-md-8 mx-auto d-flex flex-column justify-content-center">

        <!-- form card login -->
        <div class="card rounded shadow shadow-sm">
            <div class="card-header">
                <h3 class="mb-0">Inscription</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" autocomplete="off" id="formLogin" method="POST" action="register">
                    <div class="form-group">
                        <label for="uname1">Nom complet</label>
                        <input type="text" class="form-control rounded-0" name="nomComplet"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="uname1">E-mail</label>
                        <input type="email" class="form-control rounded-0" name="email" id="uname1"
                               required>
                    </div>
                    <div class="form-group">
                        <label>Télephone</label>
                        <input type="tel" class="form-control rounded-0"  name="numTelephone"
                               required pattern="[0-9]{10}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleTextarea">Adresse</label>
                        <input class="form-control rounded-0" id="exampleTextarea" name="adresse"/>
                    </div>
                    <div class="form-group">
                        <label>Mot de passe</label>
                        <input type="password" class="form-control rounded-0" id="pwd1" name="password"
                               required="" autocomplete="new-password">
                        <div class="invalid-feedback">Entrez votre mot de passe !</div>
                    </div>
                    <button type="submit" class="btn btn-success float-right" id="btnLogin">S'inscrire</button>
                </form>
            </div>
            <!--/card-block-->
        </div>
        <!-- /form card login -->

    </div>

</div>






</body>
</html>