package com.blog.redirect;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletRedirectResult;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * Classe qui permet de revenir à la page précedente via un action result
 * @author LUFFY
 *	
 */
public class RedirectSamePage extends ServletRedirectResult {

	@Override
	protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		String loc = req.getHeader("Referer");
		super.doExecute(loc, invocation);
	}
}
