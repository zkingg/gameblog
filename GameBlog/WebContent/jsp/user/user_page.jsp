<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/GameBlog/css/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="/GameBlog/css/style.css" type="text/css" />

<title>Administration du compte</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<h2>Votre Compte</h2>
					<ul class="nav nav-tabs" id="myTab">
					 	 <li class="active"><a href="#profil" data-toggle="tab">Profil</a></li>
					 	 <s:if test="#session.user.groupe == 'redacteur' || #session.user.groupe =='admin' " >
					 	 	<li><a href="#articles" data-toggle="tab">Articles</a></li>
					 	 </s:if>
					 	 <s:if test="#session.user.groupe =='admin' " >
						 	<li><a href="#administration" data-toggle="tab">Administration</a></li>
						 </s:if>
					</ul>
					<div class="tab-content">
					  	<div class="tab-pane active align_left" id="profil">
					  		<div class="hero-unit">
					  			<h3>Votre profil</h3>
						  		id : <s:text name="#session.user.id"></s:text><br/>
						  		login : <s:text name="#session.user.login"></s:text><br/>
						  		email : <s:text name="#session.user.email"></s:text><br/>
						  		groupe : <s:text name="#session.user.groupe"></s:text><br/>
						  		date creation : <s:text name="#session.user.dateActivation"></s:text><br/>
					  		</div>
					  	</div>
					  	<s:if test="#session.user.groupe == 'redacteur' || #session.user.groupe =='admin' " >
						  	<div class="tab-pane align_left" id="articles">
						  		<div class="hero-unit">
							  		<h3>Gestion des articles:</h3>
							  		<a href="/GameBlog/article/list">Listes articles</a>
							  		<h3>Gestion des catégories:</h3>
							  		<a href="/GameBlog/categorie/list">Listes catégories</a>
						  		</div>	  		
							</div>
						</s:if>
						<s:if test="#session.user.groupe =='admin' " >
						  	<div class="tab-pane align_left" id="administration">
						  		<div class="hero-unit" >
						  				<h3>Gestion users:</h3>
							  		<a href="/GameBlog/user_list" >Liste utilisateurs</a>
							  		<h3>Préférences Site</h3>
							  		<a href="/GameBlog/carousel_edit" >Gestion Carousel</a>
						  		</div>	
						  	</div>
					  	</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/layout/footer.jsp" />
</body>
</html>