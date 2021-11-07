package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;

public class IsUserValidTest {

	User goodUser1 = new User(1L, "", "John", "", "Smith", "AB1234567", 
			"goodUser1@gmail.com", Role.SUBSCRIBER);
	
	User goodUser2 = new User(0, "", "Иван", "Иванович", "Петров", "CD8912345", 
			"goodUser2@gmail.com", Role.ADMIN);
	
	User goodUser3 = new User(100001L, "", "Пётр", "Иванович", "Иванов", "DV5896541", 
			"goodUser3@gmail.com", Role.CONSULTANT);
	
	User badUser1 = new User(-1L, "", "Андрей", "Андреевич", "Андреев", "DV5896541", 
			"badUser1@gmail.com", Role.SUBSCRIBER);
	
	User badUser2 = new User(10L, "", "", "Васильевич", "Васильев", "SF4588526", 
			"badUser2@gmail.com", Role.SUBSCRIBER); 
	
	User badUser3 = new User(0, "", "Фёдор", "Михайлович", "", "TA8523697", 
			"badUser3@gmail.com", Role.SUBSCRIBER); 
	
	User badUser4 = new User(10L, "", "Василий", "Васильевич", "Васильев", "SF458852", 
			"badUser4@gmail.com", Role.SUBSCRIBER); // short passport number
	
	User badUser5 = new User(10L, "", "Василий", "Васильевич", "Васильев", "SF4588527", 
			"badUser5Agmail.com", Role.SUBSCRIBER); 
	
	User badUser6 = new User(0, "", "Александр", "Сергеевич", "Пушкин", "PC4567892", 
			"badUser6@gmail.com", null); 
	
	
	@Test
	public void goodUser1test() {
		Assert.assertTrue(InputDataValidator.isUserValid(goodUser1));
	}
	
	@Test
	public void goodUser2test() {
		Assert.assertTrue(InputDataValidator.isUserValid(goodUser2));
	}
	
	@Test
	public void goodUser3test() {
		Assert.assertTrue(InputDataValidator.isUserValid(goodUser3));
	}
	
	@Test
	public void badUser1test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser1));
	}
	
	@Test
	public void badUser2test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser2));
	}
	
	@Test
	public void badUser3test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser3));
	}
	
	@Test
	public void badUser4test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser4));
	}
	
	@Test
	public void badUser5test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser5));
	}
	
	@Test
	public void badUser6test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser6));
	}


}
