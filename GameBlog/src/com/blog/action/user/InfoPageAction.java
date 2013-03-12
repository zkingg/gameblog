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

import com.core.beans.User;
import com.core.util.GetConnection;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InfoPageAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private ArrayList<User> list;
	public ArrayList<User> getList(){return this.list;}
	
	public Pagination pagination;
	public Pagination getPagination(){return this.pagination;}
	
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	private HttpServletResponse resp;
	@Override
	public void setServletResponse(HttpServletResponse arg0) {resp = arg0;}
	
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			return "success";
		}
	}
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public class Pagination{
		public static final int ELEMENT_PAR_PAGE = 10;
		public int nb_page;
		public int current_page;
		public int tmp_page;
		public boolean has_next;
		public boolean has_prev;
		
		public Pagination(int nb_element,int current_page){
			this.nb_page=(int)(nb_element/ELEMENT_PAR_PAGE);
			if(this.nb_page % ELEMENT_PAR_PAGE != 0) this.nb_page++;
			
			this.current_page=current_page;
			has_prev = current_page > 1? true:false;
			has_next = current_page < nb_page? true:false;	
		}
	}
}
