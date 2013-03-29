package com.blog.action.article;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.core.beans.Article;
import com.core.beans.Categorie;
import com.core.util.PopupMessage;
import com.exception.ArticleNotFoundException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	private Article article;
	public Article getArticle(){return this.article;}
	
	private ArrayList<Categorie> categories;
	public ArrayList<Categorie> getCategories(){return this.categories;}
	
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
}
