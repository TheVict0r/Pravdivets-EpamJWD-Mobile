package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

public class IsNameTest {
	private final String goodName1 = "Петрович";
	private final String goodName2 = "Peterson";
	private final String goodName3 = "Жан-поль";
	
	
	private final String badName1 = "петрович";
	private final String badName2 = "Вася Ложкин";
	private final String badName3 = " Peterson";
	private final String badName4 = "Peterson ";
	private final String badName5 = "pEterson";

	@Test
	public void testGoodName1() {
		Assert.assertTrue(InputDataValidator.isName(goodName1));
	}

	@Test
	public void testGoodName2() {
		Assert.assertTrue(InputDataValidator.isName(goodName2));
	}
	
	@Test
	public void testGoodName3() {
		Assert.assertTrue(InputDataValidator.isName(goodName3));
	}
	
	@Test
	public void testBadName1() {
		Assert.assertFalse(InputDataValidator.isName(badName1));
	}
	
	@Test
	public void testBadName2() {
		Assert.assertFalse(InputDataValidator.isName(badName2));
	}
	
	@Test
	public void testBadName3() {
		Assert.assertFalse(InputDataValidator.isName(badName3));
	}
	
	@Test
	public void testBadName4() {
		Assert.assertFalse(InputDataValidator.isName(badName4));
	}
	
	@Test
	public void testBadName5() {
		Assert.assertFalse(InputDataValidator.isName(badName5));
	}
	
}
