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

<title>Création de compte</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8">
					<s:form method="post" action="inscription_valider" validate="true">
						<h1><s:text name="user.inscription.titre" ></s:text></h1>
						<s:textfield name="login" id="login" 
							label="%{getText('user.inscription.login')}" labelposition="left" cssClass="label">
						</s:textfield>
						<s:password name="mdp" id="mdp" 
							label="%{getText('user.inscription.mdp')}" labelposition="left" cssClass="label">
						</s:password>
						<s:password name="mdp2" id="mdp2" 
							label="%{getText('user.inscription.mdp2')}" labelposition="left" cssClass="label">
						</s:password>
						<s:textfield name="email" id="email" 
							label="%{getText('user.inscription.email')}" labelposition="left" cssClass="label">
						</s:textfield>
						<s:submit cssClass="btn btn-primary" value = "%{getText('user.inscription.envoyer')}" ></s:submit>
					</s:form>				
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