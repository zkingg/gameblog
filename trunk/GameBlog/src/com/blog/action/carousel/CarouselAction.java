package com.blog.action.carousel;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.blog.action.TemplateAction;
import com.core.beans.CarouselElement;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionContext;

public class CarouselAction extends TemplateAction implements ServletRequestAware{
	private File image;
	private String imageContentType;
	private String imageFileName;
	private HttpServletRequest req;
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
	
	public String validEdit(){
		Map session = ActionContext.getContext().getSession();
        try {
        	String[] tmp = imageFileName.split("\\.");
        	String extension = tmp[tmp.length-1];
        	if(!extension.equals("png") && !extension.equals("jpeg") && !extension.equals("gif") && !extension.equals("jpg") )
        		throw new Exception("Invalide image format !!");
        		
        	if(image != null){
				String filePath = req.getSession().getServletContext().getRealPath("/content/carousel/");			
				File fileToCreate = new File(filePath, this.imageFileName);
				FileUtils.copyFile(this.image, fileToCreate);
        	}
			
			if(! CarouselElement.edit(id, titre, contenu, imageFileName))
				throw new Exception("Echec mise a jour de l'element du carousel ...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			session.put("message", new PopupMessage(e.getMessage(), "error"));
		}
        
		return "success";
	}

}
