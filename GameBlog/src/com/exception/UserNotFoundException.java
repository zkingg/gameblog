package com.exception;

/**
 * Exception lev�e lorsqu'un utilisateur n'est pas trouv�
 * @author LUFFY
 *
 */
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String e){super(e);}
	public UserNotFoundException(){super();}
}