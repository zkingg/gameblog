package com.blog.action.user;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.core.beans.User;
import com.core.util.PopupMessage;
import com.exception.AlreadyActivateAccountException;
import com.opensymphony.xwork2.ActionSupport;

public class ActivationUser extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	
	public String activer(){
		try {
			if(User.activateUser(req.getParameter("key"))){
				message = new PopupMessage(getText("user.validation.reussite"), "success");	
				return "input";
			}else{
				message = new PopupMessage(getText("user.validation.echec"), "error");	
				return "input";
			}
		} catch (AlreadyActivateAccountException e) {
			message = new PopupMessage(getText("user.validation.alreadyactivate"), "info");	
			return "input";
		}
	}


}
