<%@ taglib prefix ="s" uri="/struts-tags" %>
<div class="pagination">
	<ul>
		<s:if test="pagination.has_prev">
			<li><a href="?page=1"><<</a></li>
		</s:if>
		<s:if test="(pagination.tmp_page=pagination.current_page-2) >0">
			<li><a href="?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
		</s:if>
		<s:if test="(pagination.tmp_page=pagination.current_page-1) >0">
			<li><a href="?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
		</s:if>
		
		<li class="disabled"><a href="?page=<s:property value="pagination.current_page" />" ><s:property value="pagination.current_page" /></a></li>
		
		<s:if test="(pagination.tmp_page=pagination.current_page+1) < pagination.nb_page+1 ">
			<li><a href="?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
		</s:if>
		<s:if test="(pagination.tmp_page=pagination.current_page+2) < pagination.nb_page+1 ">
			<li><a href="?page=<s:property value='pagination.tmp_page' />"><s:property value='pagination.tmp_page' /></a></li>
		</s:if>
		<s:if test="pagination.has_next">
			<li><a href="?page=<s:property value="pagination.nb_page" />">>></a></li>
		</s:if>
	</ul>
</div>
