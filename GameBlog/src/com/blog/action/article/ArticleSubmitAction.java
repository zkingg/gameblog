package com.blog.action.article;

import java.util.ArrayList;
import java.util.Map;
import com.blog.action.TemplateAction;
import com.core.beans.Article;
import com.core.beans.Categorie;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;

/**
 * Action lié a la validation de la création d'article
 * @author LUFFY
 *
 */
public class ArticleSubmitAction extends TemplateAction {
	private String titre;
	private String articletext;
	private long id_auteur;
	private String[] id_categories;
	private ArrayList<Categorie> categories;
	
	
	//getter - setter
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getArticletext() {return articletext;}
	public void setArticletext(String contenu) {this.articletext = contenu;}
	public long getId_auteur() {return id_auteur;}
	public void setId_auteur(long id_auteur) {this.id_auteur = id_auteur;}
	public String[] getId_categories() {return id_categories;}
	public void setId_categories(String[] id_categories) {	this.id_categories = id_categories;}
	public ArrayList<Categorie> getCategories(){return this.categories;}


	/**
	 * Action de validation de la création d'article
	 * @return success => index
	 * @return redirect => article/new.jsp
	 */
	@Override
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		categories = Categorie.getCategories();
			
		/*if(id_categories == null){
			session.put("message", new PopupMessage("Vous devez choisir au moins une catégories","error"));
			return "redirect";
		}*/
		
		if(titre.equals("") || articletext.equals("")){
			this.message = new PopupMessage("Vous devez remplir tout les champs","error");
			return "redirect";
		}
		
		if(Article.createArticle(id_auteur, titre, articletext, id_categories)){
			session.put("message", new PopupMessage("Article ajouté","success"));
			return "success";
		}else{
			this.message = new PopupMessage("Echec ajout article","error");
			return "redirect";
		}
	}
}
