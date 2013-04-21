package com.exception;

/**
 * Exception levée lorsqu'un utilisateur n'est pas trouvé
 * @author LUFFY
 *
 */
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String e){super(e);}
	public UserNotFoundException(){super();}
}