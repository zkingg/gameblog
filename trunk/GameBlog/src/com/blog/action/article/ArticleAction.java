package com.blog.action.article;

import java.util.Map;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport{
	
	public String newArticle(){
		Map session = ActionContext.getContext().getSession();
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			return "success";
		}
	}
}
