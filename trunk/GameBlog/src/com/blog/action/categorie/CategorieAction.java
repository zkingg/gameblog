package com.blog.action.categorie;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CategorieAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	private HttpServletResponse resp;
	@Override
	public void setServletResponse(HttpServletResponse arg0) {resp = arg0;}
	
	private Pagination pagination;
	public Pagination getPagination(){return this.pagination;}
	
	public String nom;
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	
	private ArrayList<Categorie> categories;
	public ArrayList<Categorie> getCategories(){return this.categories;}
	
	public String newCategorie(){
		Map session =  ActionContext.getContext().getSession();
		if(session.containsKey("message")){
			message = (PopupMessage) session.get("message");
			session.remove("message");
		}
		
		return "success";
	}
	
	public String validCategorie(){
		Map session =  ActionContext.getContext().getSession();
		if(Categorie.creerCategorie(nom)){
			session.put("message", new PopupMessage("Catégorie enregistrée", "info"));
			return "success";
		}else{
			session.put("message", new PopupMessage("Echec ajout catégorie", "error"));
			return "redirect";
		}
	}
	
	public String list(){
		Map session = ActionContext.getContext().getSession();
		
		if(session.containsKey("message")){
			message = (PopupMessage) session.get("message");
			session.remove("message");
		}
		
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
			pagination = new Pagination(Categorie.getNbCategories(),current_page);
			categories = Categorie.getCategories(current_page);
			return "success";
		}
	}
	
	public String edit(){
		String id = req.getParameter("id");
		String nom = req.getParameter("nom");
		try {
			if(Categorie.edit(Integer.parseInt(id),nom))	
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
	
	public String delete(){
		String id = req.getParameter("id");
		try {
			if(Categorie.delete(Integer.parseInt(id)))	
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
