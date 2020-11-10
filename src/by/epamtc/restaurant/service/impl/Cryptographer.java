package by.epamtc.restaurant.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Cryptographer {

	public static String encode(String password) throws NoSuchAlgorithmException {

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(password.getBytes());
		StringBuilder builder = new StringBuilder();

		for (byte b : bytes) {
			builder.append(String.format("%02X", b));
		}

		return builder.toString();

	}
}
