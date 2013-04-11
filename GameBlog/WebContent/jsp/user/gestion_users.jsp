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

<title>Liste des comptes</title>
</head>
<body>
	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-striped">
						<tr>
							<th>#</th>
							<th>Login</th>
							<th>Groupe</th>
							<th>Email</th>
							<th>Date Activation</th>
							<th>Cle Activation</th>
						</tr>
						<s:iterator value="list">
							<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="login"/></td>
								<td><jsp:include page="/jsp/layout/items/group_select.jsp" /></td>
								<td><s:property value="email"/></td>
								<td><s:property value="dateActivation"/></td>
								<td><s:property value="cleActivation"/></td>
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