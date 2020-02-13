package com.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Crypto is an util class to encrypt data.
 * @author Joel Gorin
 * @see <a href="https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/">
 * How to generate secure password by Lokesh Gupta</a>
 *
 */
public class Crypto {
	
	/**
	 * Encrypt strings.
	 * @param data is the string to encrypt.
	 * @return data encrypted.
	 * @throws NoSuchAlgorithmException.
	 */
	public static String encrypt(String data) throws NoSuchAlgorithmException {
        String encryptedData = null;
        try {
        	// Create MessageDigest instance for SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // Get the hash's bytes
            byte[] bytes = messageDigest.digest(data.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                		.substring(1));
            }
            // Get complete hashed password in hex format
            encryptedData = stringBuilder.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return encryptedData;
	}
}
