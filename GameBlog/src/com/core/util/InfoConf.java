package com.core.util;

public class InfoConf {
	//private final String fichier = "http://localhost:8080/GameBlog/conf/conf.ini";
	private String db;
	private String host;
	private String login;
	private String mdp;
	
	public InfoConf(){
		//*
		this.db = "gameblog";
		this.host = "localhost";
		this.login = "root";
		this.mdp = "";//*/
		
	}
	
	public String getDB(){return this.db;}
	public String getHost(){return this.host;}
	public String getLogin(){return this.login;}
	public String getMdp(){return this.mdp;}
}
