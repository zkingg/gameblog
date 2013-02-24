<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />

<title>Titre</title>
</head>
<body>
	<jsp:include page="/jsp/layout/header.jsp" />
	<div class="contenu">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span3">
					<jsp:include page="/jsp/layout/menu.jsp" />
				</div>
				<div class="span6">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit, sed do eiusmod tempor incididunt ut labore et
					dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
					exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit
					esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
					cupidatat non proident, sunt in culpa qui officia deserunt mollit
					anim id est laborum.
				
				</div>
				<div class="span3">
					<jsp:include page="/jsp/layout/rss.jsp" />
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/layout/footer.jsp" />
</body>
</html>