package com.core.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.core.util.GetConnection;
import com.core.util.Pagination;
import com.exception.ArticleNotFoundException;

public class Article {
	private long id;
	private int auteur_id;
	private String titre;
	private String article;
	private String date;
	
	public long getId(){return this.id;}
	public int getAuteur_id(){return this.auteur_id;}
	public String getTitre(){return this.titre;}
	public String getArticle(){return this.article;}
	public String getDate(){return this.date;}
	
	public Article(long id) throws ArticleNotFoundException{
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select * from articles where id ="+id);
			if(res.next()){
				this.id = id;
				this.auteur_id = res.getInt("auteur_id");
				this.titre = res.getString("titre");
				this.article = res.getString("contenu");
				this.date = res.getString("date");
			}else
				throw new ArticleNotFoundException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean createArticle(long auteur_id,String titre,String contenu,int[] id_categories ){
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			System.out.println("insert into articles(auteur_id,titre,contenu,date) value("+auteur_id+",'"+titre+"','"+contenu+"',now())");
			if(stm.executeUpdate("insert into articles(auteur_id,titre,contenu,date) values("+auteur_id+",'"+titre+"','"+contenu+"',now())",Statement.RETURN_GENERATED_KEYS) != 1 ){
				return false;
			}
			
			ResultSet res = stm.getGeneratedKeys();
			res.next();
			long id_article = res.getLong(1) ;
			
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
	
	public static ArrayList<Article> getListArticle(int page){
		ArrayList<Article> list = new ArrayList<Article>();
		
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from articles order by date desc limit "+(page-1)*Pagination.ELEMENT_PAR_PAGE+","+Pagination.ELEMENT_PAR_PAGE);
			while(res.next()){
				list.add(new Article(res.getInt("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArticleNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int getNbArticles(){
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from articles");
			res.last();
			return res.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
