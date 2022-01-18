package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.bean.Plan;

public class IsPlanValidTest {

	private final Plan goodPlan1 = new Plan(100L, "Good Plan One", 1, 2, "Description of this plan",  3, 4, 5, 6, 7, 8, 9);
	private final Plan goodPlan2 = new Plan(0, "Good Plan Two", 999, 89, "Another one description",  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan01 = new Plan();
	private final Plan badPlan02 = new Plan(-1, "Good Plan Two", 999, 89, "Another one description",  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan03 = new Plan(100, "  ", 999, 89, "Another one description",  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan04 = new Plan(100, "Plan A", 999, 89, " ",  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan05 = new Plan(100, "Plan B", 999, 89, null,  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan06 = new Plan(100, null, 999, 89, "There is no plan B",  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan07 = new Plan(100, "Plan A", -1, 89, "Some description",  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan08 = new Plan(100, "Plan A", 33, -2, "Some description",  33, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan09 = new Plan(100, "Plan A", 33, 89, "Some description",  -55, 48, 22, 100, 4, 896, 1);
	private final Plan badPlan10 = new Plan(100, "Plan A", 33, 89, "Some description",  33, -47, 22, 100, 4, 896, 1);
	private final Plan badPlan11 = new Plan(100, "Plan A", 33, 89, "Some description",  33, 48, -32, 100, 4, 896, 1);
	private final Plan badPlan12 = new Plan(100, "Plan A", 33, 89, "Some description",  33, 48, 22, -81, 4, 896, 1);
	private final Plan badPlan13 = new Plan(100, "Plan A", 33, 89, "Some description",  33, 48, 22, 100, -4, 896, 1);
	private final Plan badPlan14 = new Plan(100, "Plan A", 33, 89, "Some description",  33, 48, 22, 100, 4, -11, 1);
	private final Plan badPlan15 = new Plan(100, "Plan A", 33, 89, "Some description",  33, 48, 22, 100, 4, 896, -57);

	@Test
	public void goodPlan1Test() {
		Assert.assertTrue(InputDataValidator.isPlanValid(goodPlan1));
	}

	@Test
	public void goodPlan2Test() {
		Assert.assertTrue(InputDataValidator.isPlanValid(goodPlan2));
	}
	
	@Test
	public void badPlan01Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan01));
	}
	
	@Test
	public void badPlan02Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan02));
	}
	
	@Test
	public void badPlan03Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan03));
	}
	
	@Test
	public void badPlan04Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan04));
	}
	
	@Test
	public void badPlan05Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan05));
	}
	
	@Test
	public void badPlan06Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan06));
	}
	
	@Test
	public void badPlan07Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan07));
	}
	
	@Test
	public void badPlan08Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan08));
	}
	
	@Test
	public void badPlan09Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan09));
	}
	
	@Test
	public void badPlan10Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan10));
	}
	
	@Test
	public void badPlan11Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan11));
	}
	
	@Test
	public void badPlan12Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan12));
	}
	
	@Test
	public void badPlan13Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan13));
	}
	
	@Test
	public void badPlan14Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan14));
	}
	
	@Test
	public void badPlan15Test() {
		Assert.assertFalse(InputDataValidator.isPlanValid(badPlan15));
	}
	
	
}
