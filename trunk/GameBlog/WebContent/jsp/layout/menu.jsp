<%@ taglib prefix ="s" uri="/struts-tags" %>
<header>
	<h1>Menu</h1>
	 <s:if test="#session.user != null">
 		<s:text name="#session.user.login"></s:text>
 		<s:text name="#session.user.groupe"></s:text>
  	</s:if>
</header>