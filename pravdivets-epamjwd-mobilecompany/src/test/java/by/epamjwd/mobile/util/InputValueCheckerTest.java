package by.epamjwd.mobile.util;

import org.junit.Assert;
import org.junit.Test;

public class InputValueCheckerTest {

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

	private final String goodPassport1 = "MP1558365";
	private final String goodPassport2 = "AB5343843";
	private final String goodPassport3 = "HT3543643";
	private final String goodPassport4 = "BP4641321";
	private final String goodPassport5 = "MT5465436";

	private final String badPassport1  = "PT543654";
	private final String badPassport2  = "POS435684";
	private final String badPassport3  = "СЕ8643655"; //Cyrillic "CE"
	private final String badPassport4  = "4356845";
	private final String badPassport5  = "MT-5465436";

	@Test
	public void testGoodPhoneNumber25() {
		Assert.assertTrue(InputValueChecker.isPhone(goodPhoneNumber25));
	}
	
	@Test
	public void testGoodPhoneNumber29() {
		Assert.assertTrue(InputValueChecker.isPhone(goodPhoneNumber29));
	}
	
	@Test
	public void testGoodPhoneNumber33() {
		Assert.assertTrue(InputValueChecker.isPhone(goodPhoneNumber33));
	}
	
	@Test
	public void testGoodPhoneNumber44() {
		Assert.assertTrue(InputValueChecker.isPhone(goodPhoneNumber44));
	}
	
	@Test
	public void testBadPhoneNumber1() {
		Assert.assertFalse(InputValueChecker.isPhone(badPhoneNumber1));
	}
	
	@Test
	public void testBadPhoneNumber2() {
		Assert.assertFalse(InputValueChecker.isPhone(badPhoneNumber2));
	}
	
	@Test
	public void testBadPhoneNumber3() {
		Assert.assertFalse(InputValueChecker.isPhone(badPhoneNumber3));
	}
	
	@Test
	public void testBadPhoneNumber4() {
		Assert.assertFalse(InputValueChecker.isPhone(badPhoneNumber4));
	}
	
	@Test
	public void testBadPhoneNumber5() {
		Assert.assertFalse(InputValueChecker.isPhone(badPhoneNumber5));
	}
	
	@Test
	public void testGoodEmail1() {
		Assert.assertTrue(InputValueChecker.isEmail(goodEmail1));
	}
	
	@Test
	public void testGoodEmail2() {
		Assert.assertTrue(InputValueChecker.isEmail(goodEmail2));
	}
	
	@Test
	public void testGoodEmail3() {
		Assert.assertTrue(InputValueChecker.isEmail(goodEmail3));
	}
	
	@Test
	public void testGoodEmail4() {
		Assert.assertTrue(InputValueChecker.isEmail(goodEmail4));
	}
	
	@Test
	public void testGoodEmail5() {
		Assert.assertTrue(InputValueChecker.isEmail(goodEmail5));
	}
	
	@Test
	public void testBadEmail1() {
		Assert.assertFalse(InputValueChecker.isEmail(badEmail1));
	}
	
	@Test
	public void testBadEmail2() {
		Assert.assertFalse(InputValueChecker.isEmail(badEmail2));
	}
	
	@Test
	public void testBadEmail3() {
		Assert.assertFalse(InputValueChecker.isEmail(badEmail3));
	}
	
	@Test
	public void testBadEmail4() {
		Assert.assertFalse(InputValueChecker.isEmail(badEmail4));
	}
	
	@Test
	public void testBadEmail5() {
		Assert.assertFalse(InputValueChecker.isEmail(badEmail5));
	}
	
	@Test
	public void testGoodName1() {
		Assert.assertTrue(InputValueChecker.isName(goodName1));
	}

	@Test
	public void testGoodName2() {
		Assert.assertTrue(InputValueChecker.isName(goodName2));
	}
	
	@Test
	public void testGoodName3() {
		Assert.assertTrue(InputValueChecker.isName(goodName3));
	}
	
	@Test
	public void testBadName1() {
		Assert.assertFalse(InputValueChecker.isName(badName1));
	}
	
	@Test
	public void testBadName2() {
		Assert.assertFalse(InputValueChecker.isName(badName2));
	}
	
	@Test
	public void testBadName3() {
		Assert.assertFalse(InputValueChecker.isName(badName3));
	}
	
	@Test
	public void testBadName4() {
		Assert.assertFalse(InputValueChecker.isName(badName4));
	}
	
	@Test
	public void testBadName5() {
		Assert.assertFalse(InputValueChecker.isName(badName5));
	}
	
	@Test
	public void testGoodPassport1() {
		Assert.assertTrue(InputValueChecker.isPassport(goodPassport1));
	}

	@Test
	public void testGoodPassport2() {
		Assert.assertTrue(InputValueChecker.isPassport(goodPassport2));
	}
	
	@Test
	public void testGoodPassport3() {
		Assert.assertTrue(InputValueChecker.isPassport(goodPassport3));
	}
	
	@Test
	public void testGoodPassport4() {
		Assert.assertTrue(InputValueChecker.isPassport(goodPassport4));
	}
	
	@Test
	public void testGoodPassport5() {
		Assert.assertTrue(InputValueChecker.isPassport(goodPassport5));
	}
	
	@Test
	public void testBadPassport1() {
		Assert.assertFalse(InputValueChecker.isPassport(badPassport1));
	}

	@Test
	public void testBadPassport2() {
		Assert.assertFalse(InputValueChecker.isPassport(badPassport2));
	}
	
	@Test
	public void testBadPassport3() {
		Assert.assertFalse(InputValueChecker.isPassport(badPassport3));
	}
	
	@Test
	public void testBadPassport4() {
		Assert.assertFalse(InputValueChecker.isPassport(badPassport4));
	}
	
	@Test
	public void testBadPassport5() {
		Assert.assertFalse(InputValueChecker.isPassport(badPassport5));
	}
	
}
