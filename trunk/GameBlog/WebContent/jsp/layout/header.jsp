<%@ taglib prefix ="s" uri="/struts-tags" %>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>	
<script src="css/bootstrap/js/bootstrap.min.js"></script>
<script src="js/init_alert.js"></script>
<header>
	<h1>Header</h1>
	<s:if test="message!=null">
	 	<s:if test="message.type =='error'">
	 		<div class="alert alert-error popup_alert fade in" >
	 			<button class="close" data-dismiss="alert" >x</button>
				<s:text name="message.message"></s:text>
			</div>
	 	</s:if>
		<s:elseif test="message.type=='success'">
			<div class="alert alert-success popup_alert fade in" >
				<button class="close" data-dismiss="alert" >x</button>
				<s:text name="message.message"></s:text>
			</div>
		</s:elseif>
		<s:elseif test="message.type=='info'">
			<div class="alert alert-info popup_alert fade in" >
				<button class="close" data-dismiss="alert" >x</button>
				<s:text name="message.message"></s:text>
			</div>
		</s:elseif>
	</s:if>
</header>