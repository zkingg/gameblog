package com.core.util;

import java.awt.TrayIcon.MessageType;

public class PopupMessage {
	public String type;
	public String message;
	public PopupMessage(String message,String type){
		this.message=message;
		this.type=type;
	}
}
