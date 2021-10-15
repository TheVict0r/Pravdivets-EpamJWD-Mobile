package by.epamjwd.mobile.ZZZZtemporary;

import java.util.List;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class TestFindSubscriberListByPASSPORT {

	public static void main(String[] args) throws ServiceException {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		SubscriberService subscriberService = serviceProvider.getSubscriberService();
		
		String passport = "AB5343843";
		
		List<Subscriber> list = subscriberService.findSubscriberListByPassport(passport);

		System.out.println(list);
		
	}

}
