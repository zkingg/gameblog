package com.core.util;


/**
 * Classe pour stocker les informations a afficher dans des popup
 * @author LUFFY
 * 
 */
public class PopupMessage {
	public String type;
	public String message;
	public PopupMessage(String message,String type){
		this.message=message;
		this.type=type;
	}
}
