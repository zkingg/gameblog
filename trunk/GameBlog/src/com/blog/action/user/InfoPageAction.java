package com.blog.action.user;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.blog.action.TemplateAction;
import com.core.beans.User;
import com.core.util.GetConnection;
import com.core.util.Pagination;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InfoPageAction extends TemplateAction implements ServletRequestAware,ServletResponseAware{
	private ArrayList<User> list;
	public Pagination pagination;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {resp = arg0;}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	public ArrayList<User> getList(){return this.list;}
	public Pagination getPagination(){return this.pagination;}
	
	/**
	 * Action d'affichage de la page d'information du compte
	 * @return redirect => index
	 * @return success => user/user_page.jsp
	 */
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			return "success";
		}
	}
	
	/**
	 * Action qui affiche la page d'administration des comptes
	 * @return redirect => index
	 * @return success => user/gestion_users.jsp
	 */
	public String listUsers(){
		Map session = ActionContext.getContext().getSession();
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			int current_page = req.getParameter("page") != null? Integer.parseInt(req.getParameter("page")) : 1;
			pagination = new Pagination(User.getNbAccount(),current_page);
			list = User.getListUser(current_page);//get user only from the page
			return "success";
		}
	}
	
	/**
	 * Action de modification du groupe d'un utilisateur (Ajax)
	 * @param id : id user
	 * @param id_group : id du groupe a asigner a utilisateur
	 * @return redirect => index
	 * @return success => user/gestion_users.jsp
	 */
	public String modifGroup(){
		String id = req.getParameter("id");
		String id_group =req.getParameter("id_group");
		
		
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			if(stm.executeUpdate("update users set id_group = "+id_group+" where id ="+id) != 0)
				resp.getWriter().write("ok");
			else
				resp.getWriter().write("nok");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
