package com.blog.index;

import java.sql.SQLException;
import java.sql.Statement;
import com.core.util.GetConnection;
import com.core.util.PopupMessage;
import com.opensymphony.xwork2.ActionSupport;

public class IndexController extends ActionSupport{
	private PopupMessage message;
	public PopupMessage getMessage(){return this.message;}
	public void setMessage(PopupMessage message){this.message=message;}
	
	public String list(){
		try {
			Statement stm = GetConnection.getConnection().createStatement();
		}
		catch (SQLException e) {e.printStackTrace();}
		
		message = new PopupMessage("aaa", "error");
		return "index";
	}
}
