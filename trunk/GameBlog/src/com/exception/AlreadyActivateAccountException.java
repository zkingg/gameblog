package com.exception;

/**
 * Exeception Lev�e lors qu'un compte est d�j� activ�
 * @author LUFFY
 *
 */
public class AlreadyActivateAccountException extends Exception {
	public AlreadyActivateAccountException(String e){super(e);}
	public AlreadyActivateAccountException(){super();}
}
