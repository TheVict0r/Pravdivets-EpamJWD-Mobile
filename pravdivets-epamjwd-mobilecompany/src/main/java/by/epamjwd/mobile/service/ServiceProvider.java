package by.epamjwd.mobile.service;

import by.epamjwd.mobile.service.impl.AbonentServiceImpl;
import by.epamjwd.mobile.service.impl.AdminServiceImpl;
import by.epamjwd.mobile.service.impl.ConsultantServiceImpl;
import by.epamjwd.mobile.service.impl.NewsServiceImpl;
import by.epamjwd.mobile.service.impl.TariffPlanServiceImpl;
import by.epamjwd.mobile.service.impl.UserServiceImpl;

public class ServiceProvider {

	private AbonentService abonentService       = new AbonentServiceImpl();
	private AdminService adminService           = new AdminServiceImpl();
	private ConsultantService consultantService = new ConsultantServiceImpl();
	private NewsService newsService             = new NewsServiceImpl();
	private PlanService tariffPlanService = new TariffPlanServiceImpl();
	private UserService userService             = new UserServiceImpl();

	private ServiceProvider() {
		
	}
	
	private static class ProviderHolder{
		static final ServiceProvider INSTANCE = new ServiceProvider();
	}
	
	public static ServiceProvider getInstance() {
		return ProviderHolder.INSTANCE;
	}

	public AbonentService getAbonentService() {
		return abonentService;
	}

	public void setAbonentService(AbonentService abonentService) {
		this.abonentService = abonentService;
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
		return tariffPlanService;
	}

	public void setTariffPlanService(PlanService tariffPlanService) {
		this.tariffPlanService = tariffPlanService;
	}

	
}
