package by.epamjwd.mobile.dao;

import by.epamjwd.mobile.dao.impl.SQLSubscriberDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLAdminDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLConsultantDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLNewsDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLPlanDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLServiceDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLUserDAOImpl;

public class DAOProvider {

	private SubscriberDAO subscriberDAO = new SQLSubscriberDAOImpl();
	private AdminDAO adminDAO           = new SQLAdminDAOImpl();
	private ConsultantDAO consultantDAO = new SQLConsultantDAOImpl();
	private NewsDAO newsDao             = new SQLNewsDAOImpl();
	private UserDAO userDAO             = new SQLUserDAOImpl();
	private PlanDAO planDAO 			= new SQLPlanDAOImpl();
	private ServiceDAO serviceDAO 		= new SQLServiceDAOImpl();
	
	private DAOProvider() {
		
	}
	
	private static class ProviderHolder{
		static final DAOProvider INSTANCE = new DAOProvider();
	}
	
	public static DAOProvider getInstance() {
		return ProviderHolder.INSTANCE;
	}

	public SubscriberDAO getSubscriberDAO() {
		return subscriberDAO;
	}

	public void setSubscriberDAO(SubscriberDAO subscrierDAO) {
		this.subscriberDAO = subscrierDAO;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public ConsultantDAO getConsultantDAO() {
		return consultantDAO;
	}

	public void setConsultantDAO(ConsultantDAO consultantDAO) {
		this.consultantDAO = consultantDAO;
	}

	public NewsDAO getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDAO newsDao) {
		this.newsDao = newsDao;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public PlanDAO getPlanDAO() {
		return planDAO;
	}

	public void setPlanDAO(PlanDAO tariffPlanDAO) {
		this.planDAO = tariffPlanDAO;
	}


	public ServiceDAO getServiceDAO() {
		return serviceDAO;
	}

	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}


	
	
}
