package com.blog.action;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private String login;
	private String mdp;
	private String mdp2;
	private String email;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getMdp2() {
		return mdp2;
	}
	public void setMdp2(String mdp2) {
		this.mdp2 = mdp2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String valider(){
		
		return "nok";
	}
}
