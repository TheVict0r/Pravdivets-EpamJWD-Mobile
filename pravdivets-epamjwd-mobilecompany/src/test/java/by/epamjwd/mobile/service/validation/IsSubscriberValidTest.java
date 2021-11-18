package by.epamjwd.mobile.service.validation;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;

public class IsSubscriberValidTest {

	Subscriber goodSubscriber1 = new Subscriber(0, new Date(), 1200, "552589635", 
			new Date(), SubscriberStatus.ACTIVE, 1, 0);
	
	Subscriber goodSubscriber2 = new Subscriber(1235L, new Date(), -1200, "552589635", 
			new Date(), SubscriberStatus.BLOCKED, 3, 1589L);
	
	Subscriber goodSubscriber3 = new Subscriber(1235L, new Date(120, 11, 07), 0, "441234567", 
			new Date(120, 11, 07), SubscriberStatus.ACTIVE, 2, 458L);
	
	Subscriber badSubscriber1 = new Subscriber(-1235L, new Date(), 500, "441234567", 
			new Date(), SubscriberStatus.ACTIVE, 1, 5963247L);
	
	Subscriber badSubscriber2 = new Subscriber(1235L, new Date(119, 03, 15), 500, "991234567", 
			new Date(120, 11, 06), SubscriberStatus.ACTIVE, 1, 5963247L); //wrong phone number

	Subscriber badSubscriber3 = new Subscriber(1235L, new Date(199, 03, 15), 500, "441234567", 
			new Date(120, 11, 06), SubscriberStatus.ACTIVE, 1, 5963247L); 
	
	Subscriber badSubscriber4 = new Subscriber(1235L, new Date(120, 03, 15), 500, "441234567", 
			new Date(199, 11, 06), SubscriberStatus.ACTIVE, 1, 5963247L);
	
	Subscriber badSubscriber5 = new Subscriber(1235L, new Date(), 500, "441234567", 
			new Date(), null, 1, 5963247L);
	
	Subscriber badSubscriber6 = new Subscriber(1235L, new Date(), 500, "441234567", 
			new Date(), SubscriberStatus.ACTIVE, 0, 5963247L);
	
	Subscriber badSubscriber7 = new Subscriber(1235L, new Date(), 500, "441234567", 
			new Date(), SubscriberStatus.ACTIVE, 1, -5963247L);
	
	Subscriber badSubscriber8 = new Subscriber(1235L, null, 500, "441234567", 
			new Date(), SubscriberStatus.ACTIVE, 1, 5963247L);
	
	Subscriber badSubscriber9 = new Subscriber(1235L, new Date(), 500, "441234567", 
			null, SubscriberStatus.ACTIVE, 1, 5963247L);
	
	
	@Test
	public void goodSubscriber1Test() {
		Assert.assertTrue(InputDataValidator.isSubscriberValid(goodSubscriber1));
	}
	
	@Test
	public void goodSubscriber2Test() {
		Assert.assertTrue(InputDataValidator.isSubscriberValid(goodSubscriber2));
	}
	
	@Test
	public void goodSubscriber3Test() {
		Assert.assertTrue(InputDataValidator.isSubscriberValid(goodSubscriber3));
	}

	@Test
	public void badSubscriber1Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber1));
	}
	
	@Test
	public void badSubscriber2Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber2));
	}
	
	@Test
	public void badSubscriber3Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber3));
	}
	
	@Test
	public void badSubscriber4Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber4));
	}
	
	@Test
	public void badSubscriber5Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber5));
	}
	
	@Test
	public void badSubscriber6Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber6));
	}
	
	@Test
	public void badSubscriber7Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber7));
	}
	
	@Test
	public void badSubscriber8Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber8));
	}
	
	@Test
	public void badSubscriber9Test() {
		Assert.assertFalse(InputDataValidator.isSubscriberValid(badSubscriber9));
	}
	
	
	
	
}
