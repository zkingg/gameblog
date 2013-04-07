/**
 * 
 */
/**
 * @author LUFFY
 *
 */
package com.blog.action;

import java.util.Map;

import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author LUFFY
 * 
 * Action qui charge les informations necc�ssaire sur toutes les pages.
 * Cette classe est a d�river pour b�nificier des variable global dans vos actions
 */
public class TemplateAction extends ActionSupport{
	
	protected PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	
	public TemplateAction(){
		Map session = ActionContext.getContext().getSession();
		
		if(session.containsKey("message")){//si message stock� en session => chargement de message pour l'affichage
			message = (PopupMessage) session.get("message");
			session.remove("message");
		}
	}
	
	/**
	 * test si l'utilisateur est connect�
	 * @return true : si utilisateur connect�
	 */
	public boolean isConnect(){
		Map session = ActionContext.getContext().getSession();
		if(session.containsKey("user")){
			return true;
		}else{
			return false;
		}
	}
	
}