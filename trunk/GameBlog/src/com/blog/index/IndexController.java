package com.blog.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.core.util.GetConnection;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexController extends ActionSupport  implements ServletRequestAware{
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	public void setMessage(PopupMessage message){this.message=message;}
	
	public String list(){
		Map session = ActionContext.getContext().getSession();
		if(session.containsKey("message")){
			message = (PopupMessage)session.get("message");
			session.remove("message");
		}
			
		
		//System.out.println(req.getParameter("message"));
		//message = new PopupMessage("aaa", "error");
		return "index";
	}

}
