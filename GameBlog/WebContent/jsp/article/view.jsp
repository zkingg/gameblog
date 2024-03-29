<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/GameBlog/css/bootstrap/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/GameBlog/css/style.css" type="text/css" />

<title><s:property value="article.titre" /></title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="article_zone">
						<fieldset>
							<legend><h1><a href="/GameBlog/article/view?id=<s:property value="article.id"/>" ><s:property value="article.titre" escape="false" /></a></h1></legend>
							<p>
								<s:property value="article.article" escape="false"/>
							</p>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/layout/footer.jsp" />
</body>
</html>