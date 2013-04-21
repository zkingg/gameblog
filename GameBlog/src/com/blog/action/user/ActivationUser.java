package com.blog.action.user;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.blog.action.TemplateAction;
import com.core.beans.User;
import com.core.util.PopupMessage;
import com.exception.AlreadyActivateAccountException;
import com.opensymphony.xwork2.ActionContext;

/**
 * Action d'activation des utilisateur
 * @author LUFFY
 *
 */
public class ActivationUser extends TemplateAction implements ServletRequestAware{
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	/**
	 * Action d'activation de compte utilisateur
	 * @param key : clé d'activation du compte
	 * @return input => index
	 */
	public String activer(){
		Map session = ActionContext.getContext().getSession();
		try {
			if(User.activateUser(req.getParameter("key"))){
				session.put("message", new PopupMessage(getText("user.validation.reussite"), "success"));
				return "input";
			}else{
				session.put("message", new PopupMessage(getText("user.validation.echec"), "error"));
				return "input";
			}
		} catch (AlreadyActivateAccountException e) {
			session.put("message", new PopupMessage(getText("user.validation.alreadyactivate"), "info"));
			return "input";
		}
	}


}
