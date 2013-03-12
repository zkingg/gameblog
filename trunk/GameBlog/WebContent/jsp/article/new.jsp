<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/GameBlog/css/bootstrap/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/GameBlog/css/style.css" type="text/css" />

<title>Ajout d'un nouvel article</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8">
					<s:form>
						<h1><s:text name="article.new.titre" /></h1>
						<s:textfield name="titre" id="titre" label="%{getText('article.new.form.titre')}" labelposition="left"/>
						<s:textarea name="article" id="article_textearea" rows="10" label="%{getText('article.new.form.article')}"
									labelposition="left" />
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