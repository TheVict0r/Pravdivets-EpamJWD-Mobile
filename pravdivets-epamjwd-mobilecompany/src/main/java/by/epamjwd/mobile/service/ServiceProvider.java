package by.epamjwd.mobile.service;

import by.epamjwd.mobile.service.impl.ArticleServiceImpl;
import by.epamjwd.mobile.service.impl.BillServiceImpl;
import by.epamjwd.mobile.service.impl.CustomerServiceImpl;
import by.epamjwd.mobile.service.impl.PlanServiceImpl;
import by.epamjwd.mobile.service.impl.ServiceServiceImpl;
import by.epamjwd.mobile.service.impl.SubscriberServiceImpl;
import by.epamjwd.mobile.service.impl.UserServiceImpl;

public class ServiceProvider {

	private UserService userService             = new UserServiceImpl();
	private SubscriberService subscriberService = new SubscriberServiceImpl();
	private CustomerService customerService     = new CustomerServiceImpl();
	private ArticleService articleService       = new ArticleServiceImpl();
	private PlanService planService             = new PlanServiceImpl();
	private ServiceService serviceService       = new ServiceServiceImpl();
	private BillService billService             = new BillServiceImpl();

	private ServiceProvider() {
		
	}
	
	private static class ProviderHolder{
		static final ServiceProvider INSTANCE = new ServiceProvider();
	}
	
	public static ServiceProvider getInstance() {
		return ProviderHolder.INSTANCE;
	}

	public SubscriberService getSubscriberService() {
		return subscriberService;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}
	
	public PlanService getPlanService() {
		return planService;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public BillService getBillService() {
		return billService;
	}

	
}
