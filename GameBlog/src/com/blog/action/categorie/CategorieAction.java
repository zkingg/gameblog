package com.blog.action.categorie;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.core.beans.Categorie;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CategorieAction extends ActionSupport {
	public String nom;
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	
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
}
