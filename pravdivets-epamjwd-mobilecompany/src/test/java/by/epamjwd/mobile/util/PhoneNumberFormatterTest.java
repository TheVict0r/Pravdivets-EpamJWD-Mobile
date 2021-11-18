package by.epamjwd.mobile.util;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.service.exception.ServiceException;

public class PhoneNumberFormatterTest {

	private final String goodNumber1 = "258901235";
	private final String goodNumber2 = "295678901";
	private final String goodNumber3 = "332345678";
	private final String goodNumber4 = "449012345";
	
	private final String goodNumber1Result = "(25) 890-12-35";
	private final String goodNumber2Result = "(29) 567-89-01";
	private final String goodNumber3Result = "(33) 234-56-78";
	private final String goodNumber4Result = "(44) 901-23-45";
	
	private final String badNumber1  = "22123235";
	private final String badNumber2  = "1290123235";
	
	@Test
	public void testGoodNumber1() throws ServiceException {
		Assert.assertEquals(goodNumber1Result, PhoneFormatter.formatPhone(goodNumber1));
	}

	@Test
	public void testGoodNumber2() throws ServiceException {
		Assert.assertEquals(goodNumber2Result, PhoneFormatter.formatPhone(goodNumber2));
	}
	
	@Test
	public void testGoodNumber3() throws ServiceException {
		Assert.assertEquals(goodNumber3Result, PhoneFormatter.formatPhone(goodNumber3));
	}
	
	@Test
	public void testGoodNumber4() throws ServiceException {
		Assert.assertEquals(goodNumber4Result, PhoneFormatter.formatPhone(goodNumber4));
	}
	
	@Test(expected = ServiceException.class)
	public void testBadNumber1() throws ServiceException {
		PhoneFormatter.formatPhone(badNumber1);
	}
	
	@Test(expected = ServiceException.class)
	public void testBadNumber2() throws ServiceException {
		PhoneFormatter.formatPhone(badNumber2);
	}

}
