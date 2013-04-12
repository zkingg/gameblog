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


	$(".categorie_delete").click(function(){
		var id = $(this).attr("data");
		$(".valid_categorie_delete").attr("data",id);
		$("#ValidModal").modal('toggle');
	});
	
	$(".valid_categorie_delete").click(function(){
		var _id = $(this).attr("data");
		$.ajax({
			type: 'POST' ,
			url: '/GameBlog/categorie/delete',
			data: {
				id: _id,
			},
			success: function(data){location.reload();},
			error: function(data){alert(data);},
			complete: function(){ $("#ValidModal").modal('hide'); }
				
		});	
	});

	$(".categorie_edit").click(function(){
		var id = $(this).attr("data");
		var categorie = $("#categorie_"+id).html();
		$("#categorie_"+id).html(
				'<div class="form-search" style="margin:0px;">'+
					"<div class='input-append'>"+
						"<input type='text' class='search-query' id='nom_categorie_"+id+"' value='"+categorie+"'>"+
						"<div class='btn valid_categorie_edit' data='"+id+"' >Editer</div>"+
					"</div>"+
				'</div>'
		);
		
		$('.valid_categorie_edit').bind("click",function(){
			var _id = $(this).attr("data");
			var _nom = $("#nom_categorie_"+id).val();
			
			$.ajax({
				type: 'POST' ,
				url: '/GameBlog/categorie/edit',
				data: {
					id: _id,
					nom: _nom,
				},
				success: function(data){},
				error: function(data){alert(data);},
				complete: function(){$("#categorie_"+id).html($("#nom_categorie_"+id).val());}
					
			});
		});
	});
	
	$(".edit_element_carousel").click(function(){
		var id = $(this).attr("data");
		var titre = $("#titre_"+id).attr("data");
		var contenu = $("#contenu_"+id).attr("data");
		
		$("#edit_form_titre").val(titre);
		$("#edit_form_contenu").val(contenu);
		$("#edit_form_id").val(id);
		
		
		$("#ValidModal").modal('toggle');

	});

	$(".remove_element_carousel").click(function(){
		var id = $(this).attr("data");
		$("body").append('	<div id="ValidDeleteModal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">'+
								'<div class="modal-body">'+
									'<b>Voulez vous vraiment supprimer cet element ?</b>'+
								'</div>'+
								'<div class="modal-footer">'+
									'<button class="btn" data-dismiss="modal" aria-hidden="true">Annuler</button>'+
									'<button class="btn btn-primary valid_element_delete" data="'+id+'">Valider la suppression</button>'+
								'</div>'+
							'</div>');
		
		$("#ValidDeleteModal").modal('toggle');
		$('.valid_element_delete').bind("click",function(){
			var _id = $(this).attr("data");
			
			$.ajax({
				type: 'POST' ,
				url: '/GameBlog/carousel/delete',
				data: {
					id: _id,
				},
				success: function(data){$("#row_"+_id).remove();},
				error: function(data){alert(data);},
				complete: function(){$("#ValidDeleteModal").modal('hide');}
					
			});
		});
	});
	
	$(".up_element_carousel").click(function(){});
	$(".down_element_carousel").click(function(){});
	
	$(".ajouter_carousel_element").click(function(){
		$("#newModal").modal('toggle');
	});
});
