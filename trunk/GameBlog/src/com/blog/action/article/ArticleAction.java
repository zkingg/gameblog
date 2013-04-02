package com.blog.action.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.core.beans.Article;
import com.core.beans.Categorie;
import com.core.beans.User;
import com.core.util.Pagination;
import com.core.util.PopupMessage;
import com.exception.ArticleNotFoundException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	private HttpServletResponse resp;
	@Override
	public void setServletResponse(HttpServletResponse arg0) {resp = arg0;}
	
	private Article article;
	public Article getArticle(){return this.article;}
	
	private ArrayList<Categorie> categories;
	public ArrayList<Categorie> getCategories(){return this.categories;}
	
	private ArrayList<Article> articles;
	public ArrayList<Article> getArticles(){return this.articles;}
	
	private Pagination pagination;
	public Pagination getPagination(){return this.pagination;}
	
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
	
	public String delete(){
		String id = req.getParameter("id");
		try {
			if(User.delete(Integer.parseInt(id)))	
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
}
