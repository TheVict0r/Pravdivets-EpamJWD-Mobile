package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

public class IsPasswordTest {
	private final String goodPassword1 = "AJ84dui3";
	private final String goodPassword2 = "1fhf59aeKL";
	private final String goodPassword3 = "jhg279E2";
	
	private final String badPassword1 = "11111111";
	private final String badPassword2 = "12345678";
	private final String badPassword3 = "parol";
	private final String badPassword4 = "qwertyasd";
	private final String badPassword5 = "Masha19";
	private final String badPassword6 = "AD45 kol";

	@Test
	public void testGoodPassword1() {
		Assert.assertTrue(InputDataValidator.isPassword(goodPassword1));
	}
	
	@Test
	public void testGoodPassword2() {
		Assert.assertTrue(InputDataValidator.isPassword(goodPassword2));
	}
	
	@Test
	public void testGoodPassword3() {
		Assert.assertTrue(InputDataValidator.isPassword(goodPassword3));
	}
	
	@Test
	public void testBadPassword1() {
		Assert.assertFalse(InputDataValidator.isPassword(badPassword1));
	}

	@Test
	public void testBadPassword2() {
		Assert.assertFalse(InputDataValidator.isPassword(badPassword2));
	}
	
	@Test
	public void testBadPassword3() {
		Assert.assertFalse(InputDataValidator.isPassword(badPassword3));
	}
	
	@Test
	public void testBadPassword4() {
		Assert.assertFalse(InputDataValidator.isPassword(badPassword4));
	}
	
	@Test
	public void testBadPassword5() {
		Assert.assertFalse(InputDataValidator.isPassword(badPassword5));
	}
	
	@Test
	public void testBadPassword6() {
		Assert.assertFalse(InputDataValidator.isPassword(badPassword6));
	}
	
	
}
