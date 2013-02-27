package com.blog.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.core.util.GetConnection;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionSupport;

public class IndexController extends ActionSupport{
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	public void setMessage(PopupMessage message){this.message=message;}
	
	private ArrayList<String> users = new ArrayList<String>();
	public ArrayList<String> getUsers(){ return this.users;}
	public void setUsers(ArrayList<String> s){this.users=s;}
	
	public String list(){
		try {
			Statement stm = GetConnection.getConnection().createStatement();
			ResultSet res = stm.executeQuery("select pseudo from users");
			while(res.next()){
				users.add(res.getString("pseudo"));
			}
		}
		catch (SQLException e) {e.printStackTrace();}
		
		for( String s : users )
			System.out.println(s);
		
		message = new PopupMessage("aaa", "error");
		return "index";
	}
}
