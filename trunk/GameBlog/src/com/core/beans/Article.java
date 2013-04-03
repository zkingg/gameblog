package com.core.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.core.util.GetConnection;
import com.core.util.Pagination;
import com.exception.ArticleNotFoundException;
import com.exception.UserNotFoundException;

public class Article {
	private long id;
	private int auteur_id;
	private String titre;
	private String article;
	private String date;
	private User auteur;
	private ArrayList<Categorie> categories;
	private String str_categories;
	
	public long getId(){return this.id;}
	public int getAuteur_id(){return this.auteur_id;}
	public String getTitre(){return this.titre;}
	public String getArticle(){return this.article;}
	public String getDate(){return this.date;}
	public User getAuteur(){return this.auteur;}
	public ArrayList<Categorie> getCategories(){return this.categories;}
	public String getStr_categories(){return this.str_categories;}
	
	public Article(long id) throws ArticleNotFoundException{
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select * from articles a join users u on(a.auteur_id=u.id) where a.id ="+id);
			if(res.next()){
				this.id = id;
				this.auteur_id = res.getInt("auteur_id");
				this.titre = res.getString("titre");
				this.article = res.getString("contenu");
				this.date = res.getString("date");
				this.auteur = new User(this.auteur_id);
				
				res = stm.executeQuery("select categorie_id from contenu_categorie where article_id="+this.id);
				categories = new ArrayList<Categorie>();
				str_categories = "";
				while(res.next()){
					if(!str_categories.equals(""))//chech for separator
						str_categories += ",";
					
					Categorie c = new Categorie(res.getInt("categorie_id"));
					categories.add(c);
					str_categories += c.getNom();
				}
				//System.out.println(str_categories);
			}else
				throw new ArticleNotFoundException();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
	}
	
	public static boolean createArticle(long auteur_id,String titre,String contenu,String[] id_categories ){
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			//System.out.println("insert into articles(auteur_id,titre,contenu,date) value("+auteur_id+",'"+titre+"','"+contenu+"',now())");
			if(stm.executeUpdate("insert into articles(auteur_id,titre,contenu,date) values("+auteur_id+",'"+titre+"','"+contenu+"',now())",Statement.RETURN_GENERATED_KEYS) != 1 ){
				return false;
			}
			
			ResultSet res = stm.getGeneratedKeys();
			res.next();
			long id_article = res.getLong(1) ;
			
			for(String id_categorie : id_categories){
				if(stm.executeUpdate("insert into contenu_categorie(article_id,categorie_id) value("+id_article+","+id_categorie+")") != 1 ){
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
		
		return true;
	}
	
	public static ArrayList<Article> getListArticle(int page){
		ArrayList<Article> list = new ArrayList<Article>();
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
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
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}
		
		return list;
	}
	
	public static int getNbArticles(){
		Statement stm = null;
		try {
			stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select id from articles");
			res.last();
			return res.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}	
		
		return 0;
	}
	
	public boolean containtCategorieId(int id){
		for(Categorie c : this.categories){
			if(c.getid() == id)
				return true;
		}
		
		return false;
	}
	
	public static boolean edit(long id,String titre,String contenu,String[] id_categories ) {
		PreparedStatement stm = null;
		try {
			System.out.println(id+","+titre+","+contenu);
			stm = GetConnection.getConnection().prepareStatement("update articles set titre=? ,contenu=? where id=? ");
			System.out.println(02);
			stm.setString(1, titre);
			stm.setString(2, contenu);
			stm.setLong(3, id);
			System.out.println(03);
			stm.execute();
			stm.close();
			System.out.println(1);
			//categories
			stm = GetConnection.getConnection().prepareStatement("delete from contenu_categorie where article_id=?");
			stm.setLong(1, id);
			stm.execute();
			stm.close();
			System.out.println(2);
			for(String id_c : id_categories){
				stm = GetConnection.getConnection().prepareStatement("insert into contenu_categorie(categorie_id,article_id) value(?,?)");
				stm.setLong(1, Long.parseLong(id_c));
				stm.setLong(2, id);
				stm.execute();
				stm.close();
			}
				System.out.println(3);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {if(stm!=null) stm.close();} 
			catch (SQLException e) {}
		}	
	}
}
