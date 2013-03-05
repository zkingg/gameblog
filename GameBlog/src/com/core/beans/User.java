package com.core.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.core.util.GetConnection;
import com.core.util.Mailler;
import com.core.util.Util;
import com.exception.AlreadyActivateAccountException;

public class User {
	private int id;
	private String login;
	private String groupe;
	private String email;
	
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getGroupe() {return groupe;}
	public void setGroupe(String groupe) {this.groupe = groupe;}
	public String getLogin(){return this.login;}
	public void setLogin(String login){this.login = login;}
	
	public static boolean createUser(String login,String mdp,String email){
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
			String key = Util.md5(login+date.format(new Date()));
			System.out.println(new String(key));
			if(stm.executeUpdate("insert into users(login,mdp,email,cle_activation) values('"+login+"','"+Util.md5(mdp)+"','"+email+"','"+key+"')") == 0 ){
				return false;
			}
			else{
				String corp ="Merci de vous être inscrit sur le site de GameBlog,\r\n" +
						"Voici les informations de votre compte\n\r\tUser :"+login+
						"\r\n\tMot de passe :"+mdp+
						"\r\n\r\nVeulliez cliquer sur ce lien pour activer votre compte : http://localhost:8080/GameBlog/activation?key="+key;
				Mailler.sendMail("Activation de votre compte sur GameBlog", corp, email);
				return true;
			}	
		}
		catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	
	public static boolean activateUser(String key) throws AlreadyActivateAccountException{
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select * from user where cle_activation="+key);
			if(! res.next())
				return false;
			else{
				if(! res.getString("date_activation").equals("null")){
					throw new AlreadyActivateAccountException();
				}else{
					if(stm.executeUpdate("update user set date_activation = now() where  cle_activation="+key) == 0)
						return false;
					else
						return true;
				}			
			}
			
		}
		catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	
}
