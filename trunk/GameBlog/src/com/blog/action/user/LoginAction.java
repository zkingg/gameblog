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
		System.out.println("a");
		if((id = User.login(login,mdp)) > 0){
			//creation session
			try {
				Map session = ActionContext.getContext().getSession();
				session.remove("info");
				session.put("info", new User(id));
				System.out.println("ok");
				message = new PopupMessage(getText("user.login.sucess"), "info");
				
			} catch (UserNotFoundException e) {
				message = new PopupMessage(getText("user.login.fail"), "erreur");
			}
		}else
			message = new PopupMessage(getText("user.login.fail"), "erreur");
		
		return SUCCESS;
	}
	
	public String disconnect(){
		System.out.println("b");
		Map session = ActionContext.getContext().getSession();
		session.remove("info");
		
		return SUCCESS;
	}
}
