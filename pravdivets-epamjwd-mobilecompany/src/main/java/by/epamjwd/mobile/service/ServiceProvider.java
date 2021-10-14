package by.epamjwd.mobile.service;

import by.epamjwd.mobile.service.impl.SubscriberServiceImpl;
import by.epamjwd.mobile.service.impl.AdminServiceImpl;
import by.epamjwd.mobile.service.impl.ConsultantServiceImpl;
import by.epamjwd.mobile.service.impl.NewsServiceImpl;
import by.epamjwd.mobile.service.impl.ServiceServiceImpl;
import by.epamjwd.mobile.service.impl.PlanServiceImpl;
import by.epamjwd.mobile.service.impl.UserServiceImpl;

public class ServiceProvider {

	private UserService userService             = new UserServiceImpl();
	private SubscriberService subscriberService = new SubscriberServiceImpl();
	private AdminService adminService           = new AdminServiceImpl();
	private ConsultantService consultantService = new ConsultantServiceImpl();
	private NewsService newsService             = new NewsServiceImpl();
	private PlanService planService             = new PlanServiceImpl();
	private ServiceService serviceService       = new ServiceServiceImpl();

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

	public void setAbonentService(SubscriberService abonentService) {
		this.subscriberService = abonentService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public ConsultantService getConsultantService() {
		return consultantService;
	}

	public void setConsultantService(ConsultantService consultantService) {
		this.consultantService = consultantService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public PlanService getPlanService() {
		return planService;
	}

	public void setPlanService(PlanService tariffPlanService) {
		this.planService = tariffPlanService;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	
}
