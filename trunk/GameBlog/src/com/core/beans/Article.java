package com.core.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.core.util.GetConnection;

public class Article {
	public Article(long id){
		
	}
	
	public static boolean createArticle(long auteur_id,String titre,String contenu,int[] id_categories ){
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			if(stm.executeUpdate("insert into articles(auteur_id,titre,contenu,date) value("+auteur_id+","+titre+","+contenu+",now())") != 1 ){
				return false;
			}
			
			long id_article = stm.getGeneratedKeys().getLong(1) ;
			
			for(int id_categorie : id_categories){
				if(stm.executeUpdate("insert into contenu_categorie(article_id,categorie_id) value("+id_article+","+id_categorie+")") != 1 ){
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
