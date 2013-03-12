<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />

<title>Administration du compte</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8">
					<h2>Votre Compte</h2>
					<ul class="nav nav-tabs" id="myTab">
					 	 <li class="active"><a href="#profil" data-toggle="tab">Profil</a></li>
					 	 <li><a href="#settings" data-toggle="tab">Settings</a></li>
					 	 <s:if test="#session.user.groupe == 'redacteur' || #session.user.groupe =='admin' " >
					 	 	<li><a href="#articles" data-toggle="tab">Articles</a></li>
					 	 </s:if>
					 	 <s:if test="#session.user.groupe =='admin' " >
						 	<li><a href="#administration" data-toggle="tab">Administration</a></li>
						 </s:if>
					</ul>
					<div class="tab-content">
					  	<div class="tab-pane active" id="profil">
					  		<h3>Votre profil</h3>
					  		
					  	</div>
					  	<div class="tab-pane" id="settings">
					  		aaaa
					  	</div>
					  	<s:if test="#session.user.groupe == 'redacteur' || #session.user.groupe =='admin' " >
						  	<div class="tab-pane" id="articles">
						  		Gestion des articles
							</div>
						</s:if>
						<s:if test="#session.user.groupe =='admin' " >
						  	<div class="tab-pane" id="administration">
						  		Gestion users:<br/>
						  		<a href="user_list" >Liste utilisateurs</a>
						  	</div>
					  	</s:if>
					</div>
				</div>
				<div class="span4">
					<jsp:include page="/jsp/layout/menu.jsp" />
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/layout/footer.jsp" />
</body>
</html>