package com.blog.action.carousel;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.blog.action.TemplateAction;
import com.core.beans.Article;
import com.core.beans.CarouselElement;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;

public class CarouselAction extends TemplateAction implements ServletRequestAware,ServletResponseAware{
	private File image;
	private String imageContentType;
	private String imageFileName;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private String titre;
	private String contenu;
	private int id;
	
	/** get/set **/
	public File getImage(){ return this.image;}
	public String getImageContentType(){ return this.imageContentType;}
	public String getImageFileName(){return this.imageFileName;}
	public String getTitre(){return this.titre;}
	public String getContenu(){return this.contenu;}
	public int getId(){return this.id;}
	public void setImage(File image){this.image = image;}
	public void setImageContentType(String imageContentType){this.imageContentType = imageContentType;}
	public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
	public void setTitre(String titre){this.titre = titre;}
	public void setContenu(String contenu){this.contenu = contenu;}
	public void setId(int id){this.id = id;}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {this.req=arg0;}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {resp = arg0;}
	
	/**
	 * Action d'edition des informations du carousel
	 * @return success => retour page précédente
	 */
	public String validEdit(){
		Map session = ActionContext.getContext().getSession();
        try {
        	if(image != null){
	        	String[] tmp = imageFileName.split("\\.");
	        	String extension = tmp[tmp.length-1];
	        	if(!extension.equals("png") && !extension.equals("jpeg") && !extension.equals("gif") && !extension.equals("jpg") )
	        		throw new Exception("Invalide image format !!");
        		
				String filePath = req.getSession().getServletContext().getRealPath("/content/carousel/");			
				File fileToCreate = new File(filePath, this.imageFileName);
				FileUtils.copyFile(this.image, fileToCreate);
        	}else
        		imageFileName = "";
			
			if(! CarouselElement.edit(id, titre, contenu, imageFileName))
				throw new Exception("Echec mise a jour de l'element du carousel ...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			session.put("message", new PopupMessage(e.getMessage(), "error"));
		}
        
		return "success";
	}
	
	/**
	 * Action de suppresion d'un element du carousel (Ajax)
	 * @param id (get/post)
	 * @return null
	 */
	public String delete(){
		String id = req.getParameter("id");
		try {
			if(CarouselElement.delete(Integer.parseInt(id)))	
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
	 * Action de creation d'un nouvel element du carousel
	 * @return success => retour page précédente
	 */
	public String create(){
		Map session = ActionContext.getContext().getSession();
        try {
        	if(image != null){
	        	String[] tmp = imageFileName.split("\\.");
	        	String extension = tmp[tmp.length-1];
	        	if(!extension.equals("png") && !extension.equals("jpeg") && !extension.equals("gif") && !extension.equals("jpg") )
	        		throw new Exception("Invalide image format !!");
        		
				String filePath = req.getSession().getServletContext().getRealPath("/content/carousel/");			
				File fileToCreate = new File(filePath, this.imageFileName);
				FileUtils.copyFile(this.image, fileToCreate);
        	}else
        		imageFileName = "";
			
			if(! CarouselElement.create( titre, contenu, imageFileName))
				throw new Exception("Echec creation de l'element du carousel ...");
		} catch (Exception e) {
			session.put("message", new PopupMessage(e.getMessage(), "error"));
		}
        
		return "success";
	}
}
