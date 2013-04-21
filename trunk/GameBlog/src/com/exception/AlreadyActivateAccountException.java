package com.exception;

/**
 * Exeception Levée lors qu'un compte est déjà activé
 * @author LUFFY
 *
 */
public class AlreadyActivateAccountException extends Exception {
	public AlreadyActivateAccountException(String e){super(e);}
	public AlreadyActivateAccountException(){super();}
}
