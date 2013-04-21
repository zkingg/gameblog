package com.blog.action.categorie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.blog.action.TemplateAction;
import com.core.beans.Categorie;
import com.core.beans.User;
import com.core.util.Pagination;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;

/**
 * Groupement des actions lié aux catégories
 * @author LUFFY
 *
 */
public class CategorieAction extends TemplateAction implements ServletRequestAware,ServletResponseAware {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private Pagination pagination;
	public String nom;
	private ArrayList<Categorie> categories;
	
	/** get/set **/
	@Override
	public void setServletRequest(HttpServletRequest arg0) {req = arg0;}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {resp = arg0;}
	public Pagination getPagination(){return this.pagination;}
	public ArrayList<Categorie> getCategories(){return this.categories;}
	
	/**
	 * Action d'affichage de nouvelle catégorie
	 * @return success => categorie/new.jsp
	 * @return redirect => index
	 */
	public String newCategorie(){
		Map session =  ActionContext.getContext().getSession();
		
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			User user =(User) session.get("user");
			if(! user.isRedacteur()){
				session.put("message",new PopupMessage("Vous n'avez pas le droit d'acceder a cette page ...", "error"));
				return "redirect";
			}
		
			return "success";
		}	
	}
	
	/**
	 * Action de validation de création de catégorie
	 * @return success => index
	 * @return redirect => categorie/new.jsp
	 */
	public String validCategorie(){
		Map session =  ActionContext.getContext().getSession();
		if(Categorie.creerCategorie(nom)){
			session.put("message", new PopupMessage("Catégorie enregistrée", "info"));
			return "success";
		}else{
			session.put("message", new PopupMessage("Echec ajout catégorie", "error"));
			return "redirect";
		}
	}
	
	/**
	 * Action d'affichage de la liste des catégories
	 * @return success => categorie/list.jsp
	 * @return redirect => index
	 */
	public String list(){
		Map session = ActionContext.getContext().getSession();
			
		if(! session.containsKey("user")){
			session.put("message",new PopupMessage("Vous devez &ecirc;tre connecter pour acceder a cette page ...", "error"));
			return "redirect";
		}else{
			User user =(User) session.get("user");
			if(! user.isRedacteur()){
				session.put("message",new PopupMessage("Vous n'avez pas le droit d'acceder a cette page ...", "error"));
				return "redirect";
			}
				
			int current_page = req.getParameter("page") != null? Integer.parseInt(req.getParameter("page")) : 1;
			pagination = new Pagination(Categorie.getNbCategories(),current_page);
			categories = Categorie.getCategories(current_page);
			return "success";
		}
	}
	
	/**
	 * Action d'édition de catégorie (Ajax)
	 * @param id : id catégorie
	 * @param nom : libelle de la catégorie
	 * @return null
	 */
	public String edit(){
		String id = req.getParameter("id");
		String nom = req.getParameter("nom");
		try {
			if(Categorie.edit(Integer.parseInt(id),nom))	
				resp.getWriter().write("ok");
			else
				resp.getWriter().write("nok");
		} catch (NumberFormatException e) {
			try {resp.getWriter().write("nok - id incorrect");}
			catch (IOException e1) {e1.printStackTrace();}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Action de suppresion de catégorie (Ajax)
	 * @param id : id catégorie
	 * @return null
	 */
	public String delete(){
		String id = req.getParameter("id");
		try {
			if(Categorie.delete(Integer.parseInt(id)))	
				resp.getWriter().write("ok");
			else
				resp.getWriter().write("nok");
		} catch (NumberFormatException e) {
			try {resp.getWriter().write("nok - id incorrect");}
			catch (IOException e1) {e1.printStackTrace();}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
