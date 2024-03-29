$(document).ready(function(){
	$(".alert").alert();
	$("#btnlogin").popover({placement:'bottom'});
	$("#color-chooser").popover({placement:'bottom'}); 
	
	//init color picker lors d'un appui
	$("#color-chooser").click(function(){
		$("#colorpicker").farbtastic('#color');
		
		//lors de la validation de la couleur
	  	$(".valid-tint").click(function(){
	  		$("#color-chooser").popover("hide");
	  		getSelectionStart("tint");
	  	});
	});
	
	
	$(".popup_alert").fadeIn(400).delay(4000).fadeOut(400);//fermeture auto des popup
	
	//event changement de groupe user
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
	
	//event delete article(affichage popup)
	$(".article_delete").click(function(){
		var id = $(this).attr("data");
		$(".valid_article_delete").attr("data",id);
		$("#ValidModal").modal('toggle');
	});
	
	//validation suppresion article
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

	//affichage popup confirm delete categorie
	$(".categorie_delete").click(function(){
		var id = $(this).attr("data");
		$(".valid_categorie_delete").attr("data",id);
		$("#ValidModal").modal('toggle');
	});
	
	//validation delete categorie
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

	//event appui sur button edit categorie
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
		
		//validation changement categorie
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
				success: function(data){location.reload();},
				error: function(data){alert(data);},
				complete: function(){$("#categorie_"+id).html($("#nom_categorie_"+id).val());}
					
			});
		});
	});
	
	//affichage popup => formulaire modification info element carousel
	$(".edit_element_carousel").click(function(){
		var id = $(this).attr("data");
		var titre = $("#titre_"+id).attr("data");
		var contenu = $("#contenu_"+id).attr("data");
		
		$("#edit_form_titre").val(titre);
		$("#edit_form_contenu").val(contenu);
		$("#edit_form_id").val(id);
		
		
		$("#ValidModal").modal('toggle');

	});

	//affichage popip confim delete element carousel
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
				success: function(data){
					$("#row_"+_id).remove();
					location.reload();
				},
				error: function(data){alert(data);},
				complete: function(){$("#ValidDeleteModal").modal('hide');}
					
			});
		});
	});
		
	//affichage popup => formulaire nouvel element carousel
	$(".ajouter_carousel_element").click(function(){
		$("#newModal").modal('toggle');
	});
		
	/* events pour ajout de balise de style dans texteara new/edit article  */
  	$(".icon-italic").click(function(){getSelectionStart("italic");});
  	$(".icon-bold").click(function(){getSelectionStart("bold");});
  	$(".icon-picture").click(function(){getSelectionStart("picture");});
  	$(".icon-align-left").click(function(){getSelectionStart("left");});
  	$(".icon-align-center").click(function(){getSelectionStart("center");});
  	$(".icon-align-right").click(function(){getSelectionStart("right");});
});

//function qui permet d'ajouter des balises de styles en fonction du paramétre dans le champs #valid_article_articletext ou #valid_edit_articletext
function getSelectionStart(action)
{
    switch(action){
    case "italic" :
    	var txt = "<span style='font-style:italic;'></span>";
    	break;
    case "bold" :
	   	var txt = "<b></b>";
	   	break;
    case "tint" :
	   	var txt = "<span style='color:"+$.farbtastic("#colorpicker").color+";'></span>";
	   	break;
    case "picture" :
	   	var txt = "<img src=''></img>";
	   	break;
    case "left" :
	   	var txt = "<div style='text-align:left;'></div>";
	   	break;
    case "center" :
	   	var txt = "<div style='text-align:center;'></div>";
	   	break;
    case "right" :
      	var txt = "<div style='text-align:right;'></div>";
      	break;
    default:
    	return;
    }
    
    var Obj = document.getElementById("valid_article_articletext")?document.getElementById("valid_article_articletext"):document.getElementById("valid_edit_articletext");
    if( Obj){
        Obj.focus();
        if( typeof Obj.selectionStart != "undefined"){
            var PosDeb = Obj.selectionStart;
            var PosFin = Obj.selectionEnd;
            var Chaine = Obj.value;
            var Avant = Chaine.substring( 0 , PosDeb);
            var Apres = Chaine.substring( PosFin, Obj.textLength );
            var szSelect = Chaine.substring( PosDeb, PosFin);
            Obj.value = Avant + txt+ Apres;
            Obj.setSelectionRange( Avant.length + txt.length, Avant.length + txt.length );
            Obj.focus();
        }
        else{
            var Select = document.selection.createRange().text;
            if( Select.length > 0){
                var Chaine = document.selection.createRange();
                Chaine.text = txt ;
                Chaine.collapse();
                Chaine.select();
            }
        }
    }  
}

