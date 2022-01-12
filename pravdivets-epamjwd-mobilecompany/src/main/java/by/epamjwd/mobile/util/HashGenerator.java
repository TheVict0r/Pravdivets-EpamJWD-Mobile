package by.epamjwd.mobile.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashGenerator {

	/**
	 * Generates MD5 hash-coded string from input raw string
	 * @param rawString - input String value
	 * @return MD5 hash code of input String value
	 */
	public static String generateHash(String rawString) {
		String hashString;
		hashString = DigestUtils.md5Hex(rawString);
		return hashString;
	}	
	
}
