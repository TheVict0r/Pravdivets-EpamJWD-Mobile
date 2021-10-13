package by.epamjwd.mobile.util;

import org.junit.Assert;
import org.junit.Test;

public class LoginCheckerTest {

	private final String goodPhoneNumber25 = "258901234";
	private final String goodPhoneNumber29 = "295678901";
	private final String goodPhoneNumber33 = "332345678";
	private final String goodPhoneNumber44 = "449012345";
	
	private final String badPhoneNumber1   = "2590123456";
	private final String badPhoneNumber2   = "29901234";
	private final String badPhoneNumber3   = "951234567";
	private final String badPhoneNumber4   = "33123456a";
	private final String badPhoneNumber5   = "441234567 ";
	
	private final String goodEmail1 = "chuck_norris@mail.ru";
	private final String goodEmail2 = "j.c.van-damme@mail.ru";
	private final String goodEmail3 = "panin@google.com";
	private final String goodEmail4 = "DooM-2@idsoftware.com";
	private final String goodEmail5 = "fall.out@blackisle.com";
	
	private final String badEmail1 = "vasya pupkin@gmail.com";
	private final String badEmail2 = "vasyapupkin@gmail com";
	private final String badEmail3 = "vasyapupkin-gmail.com";
	private final String badEmail4 = "vasyapupkin@gmailcom";
	private final String badEmail5 = "vasyapupkingmail.com";
	
	
	private final String goodName1 = "Петрович";
	private final String goodName2 = "Peterson";
	private final String goodName3 = "Жан-поль";
	
	
	private final String badName1 = "петрович";
	private final String badName2 = "Вася Ложкин";
	private final String badName3 = " Peterson";
	private final String badName4 = "Peterson ";
	private final String badName5 = "pEterson";


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
	
	@Test
	public void testGoodName1() {
		Assert.assertTrue(LoginChecker.isName(goodName1));
	}

	@Test
	public void testGoodName2() {
		Assert.assertTrue(LoginChecker.isName(goodName2));
	}
	
	@Test
	public void testGoodName3() {
		Assert.assertTrue(LoginChecker.isName(goodName3));
	}
	
	@Test
	public void testBadName1() {
		Assert.assertFalse(LoginChecker.isName(badName1));
	}
	
	@Test
	public void testBadName2() {
		Assert.assertFalse(LoginChecker.isName(badName2));
	}
	
	@Test
	public void testBadName3() {
		Assert.assertFalse(LoginChecker.isName(badName3));
	}
	
	@Test
	public void testBadName4() {
		Assert.assertFalse(LoginChecker.isName(badName4));
	}
	
	@Test
	public void testBadName5() {
		Assert.assertFalse(LoginChecker.isName(badName5));
	}
	
	
}
