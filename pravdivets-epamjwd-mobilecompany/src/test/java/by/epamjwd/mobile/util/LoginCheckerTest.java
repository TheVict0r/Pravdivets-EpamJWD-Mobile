package by.epamjwd.mobile.util;

import org.junit.Assert;
import org.junit.Test;

public class LoginCheckerTest {

	private final String goodPhoneNumber24 = "241234567";
	private final String goodPhoneNumber25 = "258901234";
	private final String goodPhoneNumber29 = "295678901";
	private final String goodPhoneNumber33 = "332345678";
	private final String goodPhoneNumber44 = "449012345";
	
	private final String badPhoneNumber1   = "4490123456";
	private final String badPhoneNumber2   = "44901234";
	private final String badPhoneNumber3   = "451234567";
	private final String badPhoneNumber4   = "44123456a";
	private final String badPhoneNumber5   = "441234567 ";
	
	private final String goodEmail1   = "chuck_norris@mail.ru";
	private final String goodEmail2   = "j.c.van-damme@mail.ru";
	private final String goodEmail3   = "panin@google.com";
	private final String goodEmail4   = "DooM-2@idsoftware.com";
	private final String goodEmail5   = "fall.out@blackisle.com";
	
	private final String badEmail1   = "vasya pupkin@gmail.com";
	private final String badEmail2   = "vasyapupkin@gmail com";
	private final String badEmail3   = "vasyapupkin-gmail.com";
	private final String badEmail4   = "vasyapupkin@gmailcom";
	private final String badEmail5   = "vasyapupkingmail.com";

	@Test
	public void testGoodPhoneNumber24() {
		Assert.assertTrue(LoginChecker.isPhoneNumber(goodPhoneNumber24));
	}
	
	@Test
	public void testGoodPhoneNumber25() {
		Assert.assertTrue(LoginChecker.isPhoneNumber(goodPhoneNumber25));
	}
	
	@Test
	public void testGoodPhoneNumber29() {
		Assert.assertTrue(LoginChecker.isPhoneNumber(goodPhoneNumber29));
	}
	
	@Test
	public void testGoodPhoneNumber33() {
		Assert.assertTrue(LoginChecker.isPhoneNumber(goodPhoneNumber33));
	}
	
	@Test
	public void testGoodPhoneNumber44() {
		Assert.assertTrue(LoginChecker.isPhoneNumber(goodPhoneNumber44));
	}
	
	@Test
	public void testBadPhoneNumber1() {
		Assert.assertFalse(LoginChecker.isPhoneNumber(badPhoneNumber1));
	}
	
	@Test
	public void testBadPhoneNumber2() {
		Assert.assertFalse(LoginChecker.isPhoneNumber(badPhoneNumber2));
	}
	
	@Test
	public void testBadPhoneNumber3() {
		Assert.assertFalse(LoginChecker.isPhoneNumber(badPhoneNumber3));
	}
	
	@Test
	public void testBadPhoneNumber4() {
		Assert.assertFalse(LoginChecker.isPhoneNumber(badPhoneNumber4));
	}
	
	@Test
	public void testBadPhoneNumber5() {
		Assert.assertFalse(LoginChecker.isPhoneNumber(badPhoneNumber5));
	}
	
	@Test
	public void testGoodEmail1() {
		Assert.assertTrue(LoginChecker.isEmail(goodEmail1));
	}
	
	@Test
	public void testGoodEmail2() {
		Assert.assertTrue(LoginChecker.isEmail(goodEmail2));
	}
	
	@Test
	public void testGoodEmail3() {
		Assert.assertTrue(LoginChecker.isEmail(goodEmail3));
	}
	
	@Test
	public void testGoodEmail4() {
		Assert.assertTrue(LoginChecker.isEmail(goodEmail4));
	}
	
	@Test
	public void testGoodEmail5() {
		Assert.assertTrue(LoginChecker.isEmail(goodEmail5));
	}
	
	@Test
	public void testBadEmail1() {
		Assert.assertFalse(LoginChecker.isEmail(badEmail1));
	}
	
	@Test
	public void testBadEmail2() {
		Assert.assertFalse(LoginChecker.isEmail(badEmail2));
	}
	
	@Test
	public void testBadEmail3() {
		Assert.assertFalse(LoginChecker.isEmail(badEmail3));
	}
	
	@Test
	public void testBadEmail4() {
		Assert.assertFalse(LoginChecker.isEmail(badEmail4));
	}
	
	@Test
	public void testBadEmail5() {
		Assert.assertFalse(LoginChecker.isEmail(badEmail5));
	}
	
}
