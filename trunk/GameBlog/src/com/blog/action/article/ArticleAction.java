package com.blog.action.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.blog.action.TemplateAction;
import com.core.beans.Article;
import com.core.beans.Categorie;
import com.core.beans.User;
import com.core.util.Pagination;
import com.core.util.PopupMessage;
import com.exception.ArticleNotFoundException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends TemplateAction implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private Article article;
	private ArrayList<Categorie> categories;
	private ArrayList<Article> articles;
	private Pagination pagination;
	
	/** form return value **/
	private long id;
	private String titre;
	private String articletext;
	private String[] id_categories;
	
	/** get/set **/
	@Override 
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {resp = arg0;}
	public Article getArticle(){return this.article;}
	public ArrayList<Categorie> getCategories(){return this.categories;}
	public ArrayList<Article> getArticles(){return this.articles;}	
	public Pagination getPagination(){return this.pagination;}
	public long getId(){return this.id;}
	public void setId(long id){this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getArticletext() {return articletext;}
	public void setArticletext(String contenu) {this.articletext = contenu;}
	public String[] getId_categories() {return id_categories;}
	public void setId_categories(String[] id_categories) {	this.id_categories = id_categories;}
	
	/**
	 * Action le formulaire d'un nouvel article
	 * @return redirect => index
	 * @return success => article/new.jsp	 
	 */
	public String newArticle(){
		Map session = ActionContext.getContext().getSession();
		categories = Categorie.getCategories();
		
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			return "success";
		}
	}
	
	/**
	 * Action pour voir un article en particulier
	 * @return redirect => index
	 * @return success => article/view.jsp	
	 */
	public String view(){
		Map session = ActionContext.getContext().getSession();
		
		try {
			article = new Article(Long.parseLong(req.getParameter("id")));
		} catch (NumberFormatException e) {
			session.put("message", new PopupMessage(getText("article.view.incorrect_id"), "error"));
			return "redirect";
		} catch (ArticleNotFoundException e) {
			session.put("message", new PopupMessage(getText("article.view.id_not_found"), "error"));
			return "redirect";
		}
		
		return "success";
	}
	
	/**
	 * Action pour avoir la liste des article
	 * @return redirect => index
	 * @return success => article/list.jsp	
	 */
	public String list(){
		Map session = ActionContext.getContext().getSession();
				
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			User user =(User) session.get("user");
			if(! user.isRedacteur()){
				session.put("message",new PopupMessage("Vous n'avez pas le droit d'acceder a cette page ...", "error"));
				return "redirect";
			}
				
			int current_page = req.getParameter("page") != null? Integer.parseInt(req.getParameter("page")) : 1;
			pagination = new Pagination(Article.getNbArticles(),current_page);
			articles = Article.getListArticle(current_page);
			return "success";
		}
	}
	
	/**
	 * Action pour supprimer un article (Ajax)
	 * @return null
	 */
	public String delete(){
		String id = req.getParameter("id");
		try {
			if(Article.delete(Integer.parseInt(id)))	
				resp.getWriter().write("ok");
			else
				resp.getWriter().write("nok");
		} catch (NumberFormatException e) {
			try {resp.getWriter().write("nok - id incorrect");}
			catch (IOException e1) {e1.printStackTrace();}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Action pour afficher la page d'edition des article
	 * @return index => index
	 * @return success => article/edit.jsp	
	 * @return redirect => retour dernière page
	 */
	public String edit(){
		categories = Categorie.getCategories();
		String id = req.getParameter("id");
		Map session = ActionContext.getContext().getSession();
		
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "index";
		}else{
			User user =(User) session.get("user");
			if(! user.isRedacteur()){
				session.put("message",new PopupMessage("Vous n'avez pas le droit d'acceder a cette page ...", "error"));
				return "index";
			}
		
			try {
				article = new Article(Long.parseLong(id));
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				session.put("message", new PopupMessage("Une erreur est intervenue lors du chargement de la page", "error"));
				return "redirect";
			}
		}
	}
	
	/**
	 * Action qui valide l'edition et met a jour la base
	 * @return success => article/list.jsp	
	 * @return redirect => article/edit.jsp	
	 */
	public String validEdit(){
		Map session = ActionContext.getContext().getSession(); 
		if(id_categories == null)
			id_categories = new String[]{};
			
		if(Article.edit(id,titre,articletext,id_categories)){
			session.put("message",new PopupMessage("Informations mise à jour.", "info"));
			return "success";
		}
			
		else{
			session.put("message",new PopupMessage("Echec mise a jour article, voir log.", "error"));
			return "redirect";
		}
			
	}
}
