package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

public class IsEmailTest {

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
	
	@Test
	public void testGoodEmail1() {
		Assert.assertTrue(InputDataValidator.isEmail(goodEmail1));
	}
	
	@Test
	public void testGoodEmail2() {
		Assert.assertTrue(InputDataValidator.isEmail(goodEmail2));
	}
	
	@Test
	public void testGoodEmail3() {
		Assert.assertTrue(InputDataValidator.isEmail(goodEmail3));
	}
	
	@Test
	public void testGoodEmail4() {
		Assert.assertTrue(InputDataValidator.isEmail(goodEmail4));
	}
	
	@Test
	public void testGoodEmail5() {
		Assert.assertTrue(InputDataValidator.isEmail(goodEmail5));
	}
	
	@Test
	public void testBadEmail1() {
		Assert.assertFalse(InputDataValidator.isEmail(badEmail1));
	}
	
	@Test
	public void testBadEmail2() {
		Assert.assertFalse(InputDataValidator.isEmail(badEmail2));
	}
	
	@Test
	public void testBadEmail3() {
		Assert.assertFalse(InputDataValidator.isEmail(badEmail3));
	}
	
	@Test
	public void testBadEmail4() {
		Assert.assertFalse(InputDataValidator.isEmail(badEmail4));
	}
	
	@Test
	public void testBadEmail5() {
		Assert.assertFalse(InputDataValidator.isEmail(badEmail5));
	}
	
	
	
}
