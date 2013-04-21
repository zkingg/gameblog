package com.exception;

/**
 * Exception levée lorsqu'un element du carousel n'est pas trouvée
 * @author LUFFY
 *
 */
public class NotFoundCarouselElementExeception extends Exception {
	public NotFoundCarouselElementExeception(String msg){super(msg);}
	public NotFoundCarouselElementExeception(){}
	
}
