/**
 * 
 */
/**
 * @author LUFFY
 *
 */
package com.blog.action;

import java.util.ArrayList;
import java.util.Map;

import com.core.beans.CarouselElement;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author LUFFY
 * 
 * Action qui charge les informations neccéssaire sur toutes les pages.
 * Cette classe est a dériver pour bénificier des variable global dans vos actions
 */
public class TemplateAction extends ActionSupport{
	
	protected PopupMessage message;
	protected ArrayList<CarouselElement> carousel;
	
	/** get /set **/
	public PopupMessage getMessage(){return this.message;}
	public ArrayList<CarouselElement> getCarousel(){return this.carousel;}
	
	public TemplateAction(){
		Map session = ActionContext.getContext().getSession();
		
		if(session.containsKey("message")){//si message stocké en session => chargement de message pour l'affichage
			this.message = (PopupMessage) session.get("message");
			session.remove("message");
		}
		
		this.carousel = CarouselElement.getCarouselElements();
	}
	
	/**
	 * test si l'utilisateur est connecté
	 * @return true : si utilisateur connecté
	 */
	public boolean isConnect(){
		Map session = ActionContext.getContext().getSession();
		if(session.containsKey("user")){
			return true;
		}else{
			return false;
		}
	}
	
}