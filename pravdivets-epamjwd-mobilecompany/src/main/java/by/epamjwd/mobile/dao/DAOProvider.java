package by.epamjwd.mobile.dao;

import by.epamjwd.mobile.dao.impl.SQLSubscriberDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLAdminDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLBillDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLConsultantDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLCustomerDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLArticleDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLPlanDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLServiceDAOImpl;
import by.epamjwd.mobile.dao.impl.SQLUserDAOImpl;

public class DAOProvider {

	private SubscriberDAO subscriberDAO = new SQLSubscriberDAOImpl();
	private AdminDAO adminDAO           = new SQLAdminDAOImpl();
	private ConsultantDAO consultantDAO = new SQLConsultantDAOImpl();
	private ArticleDAO newsDao             = new SQLArticleDAOImpl();
	private UserDAO userDAO             = new SQLUserDAOImpl();
	private BillDAO billDAO             = new SQLBillDAOImpl();
	private CustomerDAO customerDAO     = new SQLCustomerDAOImpl();
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

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}


	public ConsultantDAO getConsultantDAO() {
		return consultantDAO;
	}


	public ArticleDAO getNewsDao() {
		return newsDao;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	
	public PlanDAO getPlanDAO() {
		return planDAO;
	}

	public BillDAO getBillDAO() {
		return billDAO;
	}
	
	public ServiceDAO getServiceDAO() {
		return serviceDAO;
	}


	
	
}
