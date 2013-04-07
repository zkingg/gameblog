package com.blog.action.user;

import java.util.Map;

import com.blog.action.TemplateAction;
import com.core.beans.User;
import com.core.util.PopupMessage;
import com.exception.UserNotFoundException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends TemplateAction {
	private String login;
	private String mdp;
	
	/** get/set **/
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	public String getMdp() {return mdp;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	
	/**
	 * Action de connection 
	 * @return success => page précédente
	 */
	public String execute(){
		long id;
		PopupMessage message; 
		Map session = ActionContext.getContext().getSession();
		
		if((id = User.login(login,mdp)) > 0){
			//creation session
			
			try {		
				session.remove("user");
				session.put("user", new User(id));
				message = new PopupMessage(getText("user.login.sucess"), "info");
				
			} catch (UserNotFoundException e) {
				message = new PopupMessage(getText("user.login.fail"), "error");
			}
		}else{
			message = new PopupMessage(getText("user.login.fail"), "error");
		}
		
		session.put("message", message);
		return SUCCESS;
	}
	
	/**
	 * Action de deconnection
	 * @return success => page précédente
	 */
	public String disconnect(){
		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		
		return SUCCESS;
	}
	
}
