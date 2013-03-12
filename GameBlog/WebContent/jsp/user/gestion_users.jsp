<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />

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
					<div class="pagination">
						<ul>
							<s:if test="pagination.has_prev">
								<li><a href="user_list?page=1"><<</a></li>
							</s:if>
							<s:if test="(pagination.tmp_page=pagination.current_page-2) >0">
								<li><a href="user_list?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
							</s:if>
							<s:if test="(pagination.tmp_page=pagination.current_page-1) >0">
								<li><a href="user_list?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
							</s:if>
							
							<li class="disabled"><a href="user_list?page=<s:property value="pagination.current_page" />" ><s:property value="pagination.current_page" /></a></li>
							
							<s:if test="(pagination.tmp_page=pagination.current_page+1) < pagination.nb_page+1 ">
								<li><a href="user_list?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
							</s:if>
							<s:if test="(pagination.tmp_page=pagination.current_page+2) < pagination.nb_page+1 ">
								<li><a href="user_list?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
							</s:if>
							<s:if test="pagination.has_next">
								<li><a href="user_list?page=<s:property value="pagination.nb_page" />">>></a></li>
							</s:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/layout/footer.jsp" />
</body>
</html>