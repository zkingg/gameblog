<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/GameBlog/css/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="/GameBlog/css/style.css" type="text/css" />

<title>Liste des Articles</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		
		<!-- Modal -->
		<div id="ValidModal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-body">
				<p><s:text name="article.delete.confirm" /></p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true"><s:text name="article.delete.cancel" /></button>
				<button class="btn btn-primary valid_article_delete" data=""><s:text name="article.delete.validate" /></button>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-striped">
						<tr>
							<th>#</th>
							<th>Titre</th>
							<th>Action</th>
							<th>Date</th>
							<th>Auteur</th>
							<th>Catégories</th>
						</tr>
						<s:iterator value="articles">
							<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="titre"/></td>
								<td>
									<a href="/GameBlog/article/edit?id=<s:property value="id"/>"><i class="icon-pencil"></i></a> 
									<i class="icon-remove article_delete" data="<s:property value="id"/>"></i>
								</td>
								<td><s:property value="date"/></td>
								<td><s:property value="auteur.login"/></td>
								<td><s:property value="str_categories" /></td>
							</tr>
						</s:iterator>
					</table>
					<jsp:include page="/jsp/layout/items/pagination.jsp" />
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/layout/footer.jsp" />
</body>
</html>