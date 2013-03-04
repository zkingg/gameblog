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
			String key =  login+date.format(new Date());;
			byte[] hash = MessageDigest.getInstance("MD5").digest(key.getBytes());
			
			if(stm.executeUpdate("insert into users(login,mdp,email,cle_activation) values('"+login+"','"+mdp+"','"+email+"','"+hash+"')") == 0 ){
				return false;
			}
			else
				return true;
		}
		catch (NoSuchAlgorithmException e) {e.printStackTrace();}
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
