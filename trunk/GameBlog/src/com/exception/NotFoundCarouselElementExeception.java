package com.exception;

/**
 * Exception lev�e lorsqu'un element du carousel n'est pas trouv�e
 * @author LUFFY
 *
 */
public class NotFoundCarouselElementExeception extends Exception {
	public NotFoundCarouselElementExeception(String msg){super(msg);}
	public NotFoundCarouselElementExeception(){}
	
}
