package com.core.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.core.util.GetConnection;
import com.core.util.Pagination;


public class Categorie {
	private int id;
	private String nom;
	
	public int getid(){return this.id;}
	public String getNom(){return this.nom;}
	
	public Categorie(int id){
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select nom from categories where id ="+id);
			if(res.next()){
				this.id = id;
				this.nom = res.getString("nom");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
	}
	
	public static ArrayList<Categorie> getCategories(){
		ArrayList<Categorie> list = new ArrayList<Categorie>();
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from categories ");
			while(res.next()){
				list.add(new Categorie(res.getInt("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
		
		return list;
	}
	
	public static ArrayList<Categorie> getCategories(int page){
		ArrayList<Categorie> list = new ArrayList<Categorie>();
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from categories limit "+(page-1)*Pagination.ELEMENT_PAR_PAGE+","+Pagination.ELEMENT_PAR_PAGE);
			while(res.next()){
				list.add(new Categorie(res.getInt("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
		
		return list;
	}
	
	public static boolean creerCategorie(String nom){
		PreparedStatement stm =null;
		try {
			stm = GetConnection.getConnection().prepareStatement("insert into categories(nom) values(?)");
			stm.setString(1, nom);
			stm.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
	}
	public static int getNbCategories() {
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from categories");
			res.last();
			return res.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}	
		
		return 0;
	}
	
	public static boolean delete(int id) {
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			if(stm.executeUpdate("delete from categories where id="+id) == 0)
				return false;
				
			stm.executeUpdate("delete from contenu_categorie where categorie_id="+id);//retrait categorie
				
			return true;
		}
		catch (SQLException e) {e.printStackTrace();}
		finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
		
		return false;
	}
	
	public static boolean edit(int id, String nom) {
		// TODO Auto-generated method stub
		PreparedStatement stm = null;
		
		try {
			stm = GetConnection.getConnection().prepareStatement("update categories set nom=? where id=?");
			stm.setString(1, nom);
			stm.setInt(2, id);
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
		
		return false;
	}
}
