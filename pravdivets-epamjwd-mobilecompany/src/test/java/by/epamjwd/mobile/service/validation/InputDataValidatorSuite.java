package by.epamjwd.mobile.service.validation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
	IsEmailTest.class, 
	IsNameTest.class,
	IsPassportTest.class,
	IsPhoneTest.class,
	IsSubscriberValidTest.class,
	IsUserValidTest.class
	})

@RunWith(Suite.class)

public class InputDataValidatorSuite {
	
}
