package com.exception;

/**
 * Exception lev�e lorsque qu'un article n'est pas trouv�e
 * @author LUFFY
 *
 */
public class ArticleNotFoundException extends Exception {
	public ArticleNotFoundException(String e){super(e);}
	public ArticleNotFoundException(){super();}
}