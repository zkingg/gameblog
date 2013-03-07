<%@ taglib prefix ="s" uri="/struts-tags" %>
<header>
	<h1>Menu</h1>
	 <s:if test="#session.info != null">
 		<s:text name="#session.info.login"></s:text>
  	</s:if>
</header>