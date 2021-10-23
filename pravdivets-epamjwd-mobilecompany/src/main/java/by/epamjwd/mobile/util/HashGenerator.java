package by.epamjwd.mobile.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashGenerator {

	public static String generateHash(String rawString) {
		String hashString;
		hashString = DigestUtils.md5Hex(rawString);
		return hashString;
	}	
	
}
