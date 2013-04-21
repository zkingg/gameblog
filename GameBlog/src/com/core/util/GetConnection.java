package com.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton pour BDD
 * @author LUFFY
 * 
 */
public class GetConnection {
	private static Connection con = null;
	private GetConnection(){}
	
	/**
	 * Créer et retourne le link de la connection à la BDD
	 * @return con : link BDD
	 * @throws SQLException si erreur
	 */
	public static Connection getConnection() throws SQLException{
			if(con == null || con.isClosed()){
				InfoConf info = new InfoConf();

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					con = DriverManager.getConnection("jdbc:mysql://"+info.getHost()+"/"+info.getDB()+"?useUnicode=true&characterEncoding=utf-8",info.getLogin(),info.getMdp());

				}
				catch (InstantiationException e) {e.printStackTrace();} 
				catch (IllegalAccessException e) {e.printStackTrace();}
				catch (ClassNotFoundException e) {e.printStackTrace();}
			}
				
			return con;
	}
	
}
