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

<title>Liste des Catégorie</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		
		<!-- Modal -->
		<div id="ValidModal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-body">
				<p><s:text name="categorie.delete.confirm" /></p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true"><s:text name="article.delete.cancel" /></button>
				<button class="btn btn-primary valid_categorie_delete" data=""><s:text name="article.delete.validate" /></button>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-striped">
						<tr>
							<th>#</th>
							<th>Action</th>
							<th>Nom</th>
						</tr>
						<s:iterator value="categories">
							<tr>
								<td><s:property value="id"/></td>
								<td>
									<i class="icon-pencil categorie_edit" data="<s:property value="id"/>"></i>
									<i class="icon-remove categorie_delete" data="<s:property value="id"/>"></i>
								</td>
								<td id="categorie_<s:property value="id"/>"><s:property value="nom"/></td>
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