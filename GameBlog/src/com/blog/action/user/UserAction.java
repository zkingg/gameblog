package com.blog.action.user;

import java.util.Map;

import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.omg.CORBA.ServerRequest;

import com.blog.action.TemplateAction;
import com.core.beans.User;
import com.core.util.PopupMessage;
import com.exception.AlreadyActivateAccountException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends TemplateAction implements ServletRequestAware{
	private String login;
	private String mdp;
	private String mdp2;
	private String email;
	private HttpServletRequest req;
	
	/** get/set **/
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	public String getMdp() {return mdp;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	public String getMdp2() {return mdp2;}
	public void setMdp2(String mdp2) {this.mdp2 = mdp2;}
	public String getEmail() {	return email;}
	public void setEmail(String email) {this.email = email;}
	@Override
	public void setServletRequest(HttpServletRequest req) {this.req=req;}
	
	/**
	 * Action de creation d'un utilisateur
	 * @return ok => index
	 * @return nok => user/inscription.jsp
	 */
	public String creer(){
		Map session =  ActionContext.getContext().getSession();
		if(mdp.equalsIgnoreCase(mdp2)){
			String server_adress = req.getServerName();
			server_adress += req.getServerPort() != 80 ? ":"+req.getServerPort() : ""; //ajout du port si different de 80
			if(User.createUser(login, mdp, email,server_adress)){
				session.put("message", new PopupMessage(getText("user.inscription.reussite"), "info"));
				return "ok";
			}else
				message = new PopupMessage(getText("user.inscription.erreur.echec"), "error");	
		}
		else
			addFieldError("mdp2", getText("user.inscription.erreur.mdp2"));
		
		return "nok";
	}
	

}
