<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/GameBlog/css/bootstrap/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/GameBlog/css/style.css" type="text/css" />

<title>Edition d'un article</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8 align_left">
					<s:form method="post" action="/article/valid_edit" theme="simple">
						<h1><s:text name="article.edit.titre" /></h1>
						<h2><label><s:text name="article.new.form.titre" /></label></h2>
						<s:textfield name="titre" id="titre" value="%{article.titre}" />
						<h2><label><s:text name="article.new.form.article" /></label></h2>
						<s:textarea key="articletext" rows="10" value="%{article.article}" />
						<h2><label><s:text name="article.new.form.categorie" /></label></h2>
						<div class="hero-unit" >
							<div class="row-fluid">			
							
								<s:iterator value="categories" status="i">
									<s:if test="#i.index%4 ==0" >
										</div>
										<div class="row-fluid">
									</s:if>
									<div class="span3">
										<s:if test="article.containtCategorieId(id)" >
											<s:checkbox name="id_categories" fieldValue="%{id}" value="true" /><s:property value="nom" /><br/>
										</s:if>
										<s:else>
											<s:checkbox name="id_categories" fieldValue="%{id}" value="false" /><s:property value="nom" /><br/>
										</s:else>
									</div>
								</s:iterator>
							</div>	
							<a href="/GameBlog/categorie/new" ><s:text name="article.new.linktotags"/></a>
						</div>
						
						<s:hidden name="id" value="%{article.id}" />
						<s:submit value = "%{getText('article.edit.form.submit')}" cssClass="btn btn-primary"></s:submit>
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