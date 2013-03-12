package com.blog.action.user;

import java.util.Map;

import com.core.beans.User;
import com.core.util.PopupMessage;
import com.exception.UserNotFoundException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport {
	private String login;
	private String mdp;
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	public String getMdp() {return mdp;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	
	public String execute(){
		long id;
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
	
	public String disconnect(){
		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		
		return SUCCESS;
	}
	
}
