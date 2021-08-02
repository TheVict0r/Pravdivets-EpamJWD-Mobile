package by.epamjwd.mobile.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashGeneratorTest {

	private static HashGenerator hashGenerator;
	private final String PASSWORD_1 = "hPB80D44";
	private final String PASSWORD_2 = "CY37wmAg";
	private final String PASSWORD_3 = "0RG8HutY";
	
	// all hash are MD5
	private final String PASSWORD_1_HASH = "ac8a3b296bdf07e5ddfe0d8308f1b907";
	private final String PASSWORD_2_HASH = "cfdb3452fc165cf13bb3f81f8f250adf";
	private final String PASSWORD_3_HASH = "b450f8870d4102f9d674dc81935ba5f2";
	
	
	@BeforeClass
	public static void createHashGenerator() {
		hashGenerator = new HashGenerator();
	}
	
	@Test
	public void testPassword1() {
		Assert.assertEquals(PASSWORD_1_HASH, hashGenerator.generateHash(PASSWORD_1));
	}

	@Test
	public void testPassword2() {
		Assert.assertEquals(PASSWORD_2_HASH, hashGenerator.generateHash(PASSWORD_2));
	}
	
	@Test
	public void testPassword3() {
		Assert.assertEquals(PASSWORD_3_HASH, hashGenerator.generateHash(PASSWORD_3));
	}
	
	

}
