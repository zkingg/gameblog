package com.blog.action.article;

import java.util.Map;

import com.core.beans.Article;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleSubmitAction extends ActionSupport {
	private String titre;
	private String contenu;
	private long id_auteur;
	private int[] id_categories;
	private PopupMessage message;

	
	//getter - setter
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getContenu() {return contenu;}
	public void setContenu(String contenu) {this.contenu = contenu;}
	public long getId_auteur() {return id_auteur;}
	public void setId_auteur(long id_auteur) {this.id_auteur = id_auteur;}
	public int[] getId_categories() {return id_categories;}
	public void setId_categories(int[] id_categories) {	this.id_categories = id_categories;}
	public PopupMessage getMessage(){return this.message;}
	public void setMessage(PopupMessage message){this.message=message;}



	public String submit(){
		Map session = ActionContext.getContext().getSession();
		System.out.println(titre+","+contenu);
		if(Article.createArticle(id_auteur, titre, contenu, id_categories)){
			session.put("message", new PopupMessage("Article ajouté","success"));
			return "success";
		}else{
			session.put("message", new PopupMessage("Echec ajout article","error"));
			return "redirect";
		}
	}
}
