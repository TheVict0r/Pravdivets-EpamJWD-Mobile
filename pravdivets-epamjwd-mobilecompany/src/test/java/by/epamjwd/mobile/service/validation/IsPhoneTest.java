package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

public class IsPhoneTest {

	private final String goodPhoneNumber25 = "258901234";
	private final String goodPhoneNumber29 = "295678901";
	private final String goodPhoneNumber33 = "332345678";
	private final String goodPhoneNumber44 = "449012345";
	
	private final String badPhoneNumber1   = "2590123456";
	private final String badPhoneNumber2   = "29901234";
	private final String badPhoneNumber3   = "951234567";
	private final String badPhoneNumber4   = "33123456a";
	private final String badPhoneNumber5   = "441234567 ";
	
	@Test
	public void testGoodPhoneNumber25() {
		Assert.assertTrue(InputDataValidator.isPhone(goodPhoneNumber25));
	}
	
	@Test
	public void testGoodPhoneNumber29() {
		Assert.assertTrue(InputDataValidator.isPhone(goodPhoneNumber29));
	}
	
	@Test
	public void testGoodPhoneNumber33() {
		Assert.assertTrue(InputDataValidator.isPhone(goodPhoneNumber33));
	}
	
	@Test
	public void testGoodPhoneNumber44() {
		Assert.assertTrue(InputDataValidator.isPhone(goodPhoneNumber44));
	}
	
	@Test
	public void testBadPhoneNumber1() {
		Assert.assertFalse(InputDataValidator.isPhone(badPhoneNumber1));
	}
	
	@Test
	public void testBadPhoneNumber2() {
		Assert.assertFalse(InputDataValidator.isPhone(badPhoneNumber2));
	}
	
	@Test
	public void testBadPhoneNumber3() {
		Assert.assertFalse(InputDataValidator.isPhone(badPhoneNumber3));
	}
	
	@Test
	public void testBadPhoneNumber4() {
		Assert.assertFalse(InputDataValidator.isPhone(badPhoneNumber4));
	}
	
	@Test
	public void testBadPhoneNumber5() {
		Assert.assertFalse(InputDataValidator.isPhone(badPhoneNumber5));
	}
	

	
	
	
}
