package com.core.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.core.util.GetConnection;
import com.exception.NotFoundCarouselElementExeception;

/**
 * 
 * @author LUFFY
 * Beans : element du carousel
 */
public class CarouselElement {
	private int id;
	private String href;
	private String contenu;
	private String titre;
	
	/** get/set **/
	public int getId(){return this.id;}
	public String getHref(){return this.href;}
	public String getContenu(){return this.contenu;}
	public String getTitre(){return this.titre;}
	
	public CarouselElement(int id) throws NotFoundCarouselElementExeception{
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select * from carouselelements where id="+id);
			
			if(res.next()){
				this.id = res.getInt("id");
				this.href = res.getString("href");
				this.contenu = res.getString("contenu");
				this.titre = res.getString("titre");
			}else
				throw new NotFoundCarouselElementExeception();
		} catch (SQLException e) {e.printStackTrace();} 
		finally{
			try {if(stm != null) stm.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
		
	}
	
	/**
	 * Return la liste des elements du carousel
	 * @return elements du carousel
	 */
	public static ArrayList<CarouselElement> getCarouselElements(){
		ArrayList<CarouselElement> list = new ArrayList<CarouselElement>();
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from carouselelements");
			
			while(res.next()){
				list.add(new CarouselElement(res.getInt("id")));
			}
		}
		catch (SQLException e) {e.printStackTrace();}
		catch (NotFoundCarouselElementExeception e) {e.printStackTrace();} 
		finally{
			try {if(stm != null) stm.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
		
		return list;
	}
	
	/**
	 * Permet de mettre a jour les informations en base d'un element de carousel 
	 * @param id
	 * @param titre
	 * @param contenu
	 * @param nom_img
	 * @return true : si changement ok
	 */
	public static boolean edit(int id,String titre,String contenu,String nom_img){
		PreparedStatement stm = null;
		
		try {
			
			stm=GetConnection.getConnection().prepareStatement("update carouselelements set titre=?, contenu=?, href=? where id=? ");
			stm.setString(1, titre);
			stm.setString(2, contenu);
			stm.setString(3, nom_img);
			stm.setInt(4, id);
			stm.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
}
