package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.bean.Service;

public class IsServiceValidTest {
	private final Service goodService1 = new Service(0, "Название", 99, "Описание услуги");
	private final Service badService1 = new Service(-1, "Название", 99, "Описание услуги");
	private final Service badService2 = new Service(0, null, 99, "Описание услуги");
	private final Service badService3 = new Service(0, " ", 99, "Описание услуги");
	private final Service badService4 = new Service(0, "Название", -99, "Описание услуги");
	private final Service badService5 = new Service(0, "Название", 99, null);
	private final Service badService6 = new Service(0, "Название", 99, " ");
	private final Service badService7 = new Service();
	
	
	@Test
	public void goodService1Test() {
		Assert.assertTrue(InputDataValidator.isServiceValid(goodService1));
	}
	
	@Test
	public void badService1Test() {
		Assert.assertFalse(InputDataValidator.isServiceValid(badService1));
	}
	
	@Test
	public void badService2Test() {
		Assert.assertFalse(InputDataValidator.isServiceValid(badService2));
	}
	
	@Test
	public void badService3Test() {
		Assert.assertFalse(InputDataValidator.isServiceValid(badService3));
	}
	
	@Test
	public void badService4Test() {
		Assert.assertFalse(InputDataValidator.isServiceValid(badService4));
	}
	
	@Test
	public void badService5Test() {
		Assert.assertFalse(InputDataValidator.isServiceValid(badService5));
	}
	
	@Test
	public void badService6Test() {
		Assert.assertFalse(InputDataValidator.isServiceValid(badService6));
	}
	
	@Test
	public void badService7Test() {
		Assert.assertFalse(InputDataValidator.isServiceValid(badService7));
	}
	
	
}
