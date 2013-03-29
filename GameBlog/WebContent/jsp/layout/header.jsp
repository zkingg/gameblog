<%@ taglib prefix ="s" uri="/struts-tags" %>
<script type="text/javascript" src="/GameBlog/js/jquery-1.9.1.js"></script>	
<script src="/GameBlog/css/bootstrap/js/bootstrap.min.js"></script>
<script src="/GameBlog/js/init_alert.js"></script>
<header>
	<div class="navbar" >
		<div class="navbar-inner" >
			<a  class="brand" href="/GameBlog/index">GameBlog</a>
			<ul class="nav">
				<li><a href="#" >Action 1</a></li>
				<li><a href="#" >Action 2</a></li>
				<li><a href="#" >Action 3</a></li>
				<li class="divider-vertical" ></li>		
				
			</ul>
			<div class="nav pull-right">
				<s:if test="#session.user == null">
					<li class="pull-right"><a id="btnlogin" data-toggle="popover" title="Connexion" data-content="<jsp:include page='/jsp/layout/items/login_form.jsp' />" ><i class="icon-user icon-white"></i>  Se connecter</a></li>		
				</s:if>
				<s:else>
					<s:if test="#session.user.groupe == 'redacteur' || #session.user.groupe =='admin' ">
						<li class="pull-right"><a href="/GameBlog/article/new" >Ajouter un article</a></li>
					</s:if>
					<li class="pull-right"><a href="/GameBlog/user_page" >Votre Compte</a></li>
					<li class="pull-right"><a href="/GameBlog/disconnect" >Se deconnecter</a></li>  
				</s:else>		 
			</div>
		</div>
	</div>
	<!-- Caroussel -->
	<div id="myCarousel" class="carousel slide">
		<!-- Carousel items -->
		<div class="carousel-inner">
			<div class="active item">
				<img src="/GameBlog/img/dragon.jpg" alt="" class="carousel-img"/>
				<div class="carousel-caption">
					<h4>First Thumbnail label</h4>
					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
				</div>
			</div>
			<div class="item">
				<img src="/GameBlog/img/color.jpg" alt="" class="carousel-img"/>
				<div class="carousel-caption">
					<h4>Second Thumbnail label</h4>
					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
				</div>
			</div>
			<div class="item">
				<img src="/GameBlog/img/ME3.jpg" alt="" class="carousel-img"/>
				<div class="carousel-caption">
					<h4>Third Thumbnail label</h4>
					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
				</div>
			</div>
		</div>
		<!-- Carousel nav -->
		<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>
	
	<!--  Popup Message -->
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