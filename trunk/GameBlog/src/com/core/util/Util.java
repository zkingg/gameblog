package com.core.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author LUFFY
 * Classe utilitaire
 */
public class Util {

	/**
	 * Créer un md5 sur 32 caractére
	 * @param s : string a encoder
	 * @return hashtext : md5 de la chaine passée 
	 */
	public static String md5(String s) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(s.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
