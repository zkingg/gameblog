package com.core.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.core.util.GetConnection;


public class Categorie {
	private int id;
	private String nom;
	
	public int getid(){return this.id;}
	public String getNom(){return this.nom;}
	
	public Categorie(int id){
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select nom from categories where id ="+id);
			if(res.next()){
				this.id = id;
				this.nom = res.getString("nom");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Categorie> getCategories(){
		ArrayList<Categorie> list = new ArrayList<Categorie>();
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from categories ");
			while(res.next()){
				list.add(new Categorie(res.getInt("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean creerCategorie(String nom){
		try {
			PreparedStatement stm = GetConnection.getConnection().prepareStatement("insert into categories(nom) values(?)");
			stm.setString(1, nom);
			stm.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
