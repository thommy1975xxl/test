package das.tickets.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordService {

	private final static String HASH = "MD5";

	public static String createHashedPassword(String password) {
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance(HASH);
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		} catch (NoSuchAlgorithmException nsae) {
			// ignore
		}
		return hashword;
	}

}
