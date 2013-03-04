package com.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	private static Connection con = null;
	private GetConnection(){}
	public static Connection getConnection() throws SQLException{
			if(con == null || con.isClosed()){
				InfoConf info = new InfoConf();

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					con = DriverManager.getConnection("jdbc:mysql://"+info.getHost()+"/"+info.getDB(),info.getLogin(),info.getMdp());
				}
				catch (InstantiationException e) {e.printStackTrace();} 
				catch (IllegalAccessException e) {e.printStackTrace();}
				catch (ClassNotFoundException e) {e.printStackTrace();}
			}
				
			return con;
	}
}