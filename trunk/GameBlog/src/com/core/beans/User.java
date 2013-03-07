package com.core.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
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
import com.exception.UserNotFoundException;

public class User {
	private long id;
	private String login;
	private String groupe;
	private String email;
	
	
	public User(long id) throws UserNotFoundException {
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select login,email,g.nom as groupe from users u join groups g on(u.id_group = g.id)  where u.id="+id);
			if(! res.next())
				throw new UserNotFoundException();
			
			this.id =id;
			this.login=res.getString("login");
			this.groupe=res.getString("groupe");
			this.email=res.getString("email");
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public long getId() {return id;}
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
			ResultSet res = stm.executeQuery("select * from users where cle_activation='"+key+"'");
			if(! res.next())
				return false;
			else{
				if(! (res.getString("date_activation") == null)){
					throw new AlreadyActivateAccountException();
				}else{
					if(stm.executeUpdate("update users set date_activation = now() where  cle_activation='"+key+"'") == 0)
						return false;
					else
						return true;
				}			
			}
			
		}
		catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	
	public static long login(String login,String mdp){
		try {
			PreparedStatement stm = GetConnection.getConnection().prepareStatement("select id from users where mdp = ? and login=? and date_activation is not null");
			stm.setString(1,Util.md5(mdp));//mdp
			stm.setString(2,login);//login
			ResultSet res = stm.executeQuery();
			if(res.next())
				return res.getInt("id");
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
