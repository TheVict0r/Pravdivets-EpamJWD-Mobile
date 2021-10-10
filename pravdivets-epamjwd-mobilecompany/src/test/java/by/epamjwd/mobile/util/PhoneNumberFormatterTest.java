package by.epamjwd.mobile.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epamjwd.mobile.service.exception.ServiceException;

public class PhoneNumberFormatterTest {

	private static PhoneNumberFormatter formatter;
	
	private final int goodNumber1 = 258901235;
	private final int goodNumber2 = 295678901;
	private final int goodNumber3 = 332345678;
	private final int goodNumber4 = 449012345;
	
	private final String goodNumber1Result = "(25) 890-12-35";
	private final String goodNumber2Result = "(29) 567-89-01";
	private final String goodNumber3Result = "(33) 234-56-78";
	private final String goodNumber4Result = "(44) 901-23-45";
	
	private final int badNumber1  = 22123235;
	private final int badNumber2  = 1290123235;

	@BeforeClass
	public static void createPhoneNumberFormatter() {
		formatter = new PhoneNumberFormatter();
	}

	@Test
	public void testGoodNumber1() throws ServiceException {
		Assert.assertEquals(goodNumber1Result, formatter.formatPhomeNumber(goodNumber1));
	}

	@Test
	public void testGoodNumber2() throws ServiceException {
		Assert.assertEquals(goodNumber2Result, formatter.formatPhomeNumber(goodNumber2));
	}
	
	@Test
	public void testGoodNumber3() throws ServiceException {
		Assert.assertEquals(goodNumber3Result, formatter.formatPhomeNumber(goodNumber3));
	}
	
	@Test
	public void testGoodNumber4() throws ServiceException {
		Assert.assertEquals(goodNumber4Result, formatter.formatPhomeNumber(goodNumber4));
	}
	
	@Test(expected = ServiceException.class)
	public void testBadNumber1() throws ServiceException {
		formatter.formatPhomeNumber(badNumber1);
	}
	
	@Test(expected = ServiceException.class)
	public void testBadNumber2() throws ServiceException {
		formatter.formatPhomeNumber(badNumber2);
	}

}
