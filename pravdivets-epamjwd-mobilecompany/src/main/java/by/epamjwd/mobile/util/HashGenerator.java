package by.epamjwd.mobile.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashGenerator {

	public String generateHash(String password) {
		String hash;
		hash = DigestUtils.md5Hex(password);
		return hash;
	}	
	
}
