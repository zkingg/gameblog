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

<title>Accueil</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="articles_zone">
						<s:iterator value="articles">						
							<fieldset >
								<legend><h1><a href="/GameBlog/article/view?id=<s:property value="id"/>" ><s:property escape="false" value="titre" /></a></h1></legend>
								<div class="well"><s:property escape="false" value="article"  /></div>
								<blockquote>
									<!-- Auteur -->
									<small><s:property value="date" /></small>
								</blockquote>
							</fieldset>
						</s:iterator>	
					</div>
					<jsp:include page="/jsp/layout/items/pagination.jsp" />
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/layout/footer.jsp" />
</body>
</html>