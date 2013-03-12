<%@ taglib prefix ="s" uri="/struts-tags" %>
<select name="group_select" id="<s:property value='id'/>" >
	<option value="1" <s:if test="groupe=='user' ">selected='selected'</s:if> >user</option>
	<option value="2" <s:if test="groupe=='redacteur' ">selected='selected'</s:if> >redacteur</option>
	<option value="3" <s:if test="groupe=='admin' ">selected='selected'</s:if> >admin</option>
	<option value="4" <s:if test="groupe=='banni' ">selected='selected'</s:if> >banni</option>
</select>