package com.blog.index;


import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.core.beans.Article;
import com.core.util.Pagination;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport  implements ServletRequestAware{
	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	public void setMessage(PopupMessage message){this.message=message;}
	
	private ArrayList<Article> articles;
	public ArrayList<Article> getArticles(){return this.articles;}
	
	private Pagination pagination;
	public Pagination getPagination(){return this.pagination;}
	
	public String execute(){
		Map session = ActionContext.getContext().getSession();
		if(session.containsKey("message")){
			message = (PopupMessage)session.get("message");
			session.remove("message");
		}
			
		int current_page = req.getParameter("page") != null? Integer.parseInt(req.getParameter("page")) : 1;
		pagination = new Pagination(Article.getNbArticles(),current_page);
		articles = Article.getListArticle(current_page);
		//System.out.println(req.getParameter("message"));
		//message = new PopupMessage("aaa", "error");
		return "index";
	}

}
