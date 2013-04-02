$(document).ready(function(){
	$(".alert").alert();
	$("#btnlogin").popover({placement:'bottom'}); 
	
	$(".popup_alert").fadeIn(400).delay(4000).fadeOut(400);//fermeture auto des popup
	
	
	$("select[name=group_select]").change(function(){
		var _id = $(this).attr('id');
		var _id_group= $(this,'option:selected').val();

		$.ajax({
			type: 'POST' ,
			url: 'user_list_modif_group_ajax',
			data: {
				id: _id,
				id_group: _id_group,
			},
			success: function(data){
				//alert(data);
				if(data != "ok")
					alert("Echec mise a jour");
			}
			
		});
	});
	
	
	$(".article_delete").click(function(){
		var id = $(this).attr("data");
		$(".valid_article_delete").attr("data",id);
		$("#ValidModal").modal('toggle');
	});
	
	$(".valid_article_delete").click(function(){
		var _id = $(this).attr("data");
		$.ajax({
			type: 'POST' ,
			url: '/GameBlog/article/delete',
			data: {
				id: _id,
			},
			success: function(data){location.reload();},
			error: function(data){alert(data);},
			complete: function(){ $("#ValidModal").modal('hide'); }
				
		});	
	});
});