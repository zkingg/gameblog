<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="s" uri="/struts-tags" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/GameBlog/css/bootstrap/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/GameBlog/css/style.css" type="text/css" />

<title>Configuration du carousel</title>
</head>
<body>
	<!--  Popup - edit -->
	<s:form action="carousel/validEdit" method="POST" enctype="multipart/form-data" theme="simple">
	<div id="ValidModal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-header">
			<h2><s:text name="carousel.edit.form.header" /></h2>
		</div>
		<div class="modal-body">
			<label><s:text name="article.new.form.titre" /></label>
			<s:textfield name="titre" id="edit_form_titre" cssClass="article_textearea" /><br/>
			 <label><s:text name="carousel.edit.form.contenu" /></label>
			 <s:textarea name="contenu" id="edit_form_contenu" cssClass="article_textearea" rows="5"></s:textarea>
			 <label><s:text name="carousel.edit.form.choixfichier" /></label>
			<s:file name="image" label='%{getText("carousel.edit.form.choixfichier")}' size="70" required="false"/>	
			<s:hidden name="id" id="edit_form_id"></s:hidden>		
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true"><s:text name="article.delete.cancel" /></button>
			<s:submit cssClass="btn btn-primary" value='%{getText("article.edit.form.submit")}' id="edit_form_valid" />
		</div>
	</div>
	</s:form>

	<div class="contenu">
		<jsp:include page="/jsp/layout/header.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8 ">
					<h1>Configuration du carousel</h1>
					<table class="table table-hover">
						<tr>
							<th>Action</th>
							<th>Image</th>
							<th>Description</th>
						</tr>
						<s:iterator value="element_carousel" status="i" >
							<tr id="row_<s:property value='id'/>">
								<td class="va_middle" >
									<i class="icon-edit edit_element_carousel" data="<s:property value='id'/>"></i>
									<i class="icon-remove remove_element_carousel" data="<s:property value='id'/>"></i><br/>
									<i class="icon-arrow-up up_element_carousel" data="<s:property value='id'/>"></i>
									<i class="icon-arrow-down down_element_carousel" data="<s:property value='id'/>"></i>
								</td>
								<td class="va_middle">
									<img src="/GameBlog/content/carousel/<s:property value='href' />" id="image_<s:property value='id'/>" alt="" class="img-rounded" height="140" width="140"/>
								</td>
								<td class="va_middle" >
									<fieldset class="">
											<legend>
												<h2 id="titre_<s:property value='id'/>" ><s:property value="titre" /></h2>
											</legend>
											<p id="contenu_<s:property value='id'/>">
												<s:property value="contenu" />
											</p>
									</fieldset>
								</td>
							</tr>	
						</s:iterator>
					</table>
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