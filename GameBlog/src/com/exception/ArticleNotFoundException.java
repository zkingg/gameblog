package com.exception;

/**
 * Exception levée lorsque qu'un article n'est pas trouvée
 * @author LUFFY
 *
 */
public class ArticleNotFoundException extends Exception {
	public ArticleNotFoundException(String e){super(e);}
	public ArticleNotFoundException(){super();}
}