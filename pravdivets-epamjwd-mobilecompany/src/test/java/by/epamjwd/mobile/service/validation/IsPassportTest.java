package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

public class IsPassportTest {
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
	public void testGoodPassport1() {
		Assert.assertTrue(InputDataValidator.isPassport(goodPassport1));
	}

	@Test
	public void testGoodPassport2() {
		Assert.assertTrue(InputDataValidator.isPassport(goodPassport2));
	}
	
	@Test
	public void testGoodPassport3() {
		Assert.assertTrue(InputDataValidator.isPassport(goodPassport3));
	}
	
	@Test
	public void testGoodPassport4() {
		Assert.assertTrue(InputDataValidator.isPassport(goodPassport4));
	}
	
	@Test
	public void testGoodPassport5() {
		Assert.assertTrue(InputDataValidator.isPassport(goodPassport5));
	}
	
	@Test
	public void testBadPassport1() {
		Assert.assertFalse(InputDataValidator.isPassport(badPassport1));
	}

	@Test
	public void testBadPassport2() {
		Assert.assertFalse(InputDataValidator.isPassport(badPassport2));
	}
	
	@Test
	public void testBadPassport3() {
		Assert.assertFalse(InputDataValidator.isPassport(badPassport3));
	}
	
	@Test
	public void testBadPassport4() {
		Assert.assertFalse(InputDataValidator.isPassport(badPassport4));
	}
	
	@Test
	public void testBadPassport5() {
		Assert.assertFalse(InputDataValidator.isPassport(badPassport5));
	}
}
