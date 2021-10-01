package by.epamjwd.mobile.dao;

import by.epamjwd.mobile.dao.impl.SQLAbonentDAO;
import by.epamjwd.mobile.dao.impl.SQLAdminDAO;
import by.epamjwd.mobile.dao.impl.SQLConsultantDAO;
import by.epamjwd.mobile.dao.impl.SQLNewsDAO;
import by.epamjwd.mobile.dao.impl.SQLPlanDAO;
import by.epamjwd.mobile.dao.impl.SQLServiceDAO;
import by.epamjwd.mobile.dao.impl.SQLUserDAO;

public class DAOProvider {

	private AbonentDAO abonentDAO       = new SQLAbonentDAO();
	private AdminDAO adminDAO           = new SQLAdminDAO();
	private ConsultantDAO consultantDAO = new SQLConsultantDAO();
	private NewsDAO newsDao             = new SQLNewsDAO();
	private UserDAO userDAO             = new SQLUserDAO();
	private PlanDAO planDAO 			= new SQLPlanDAO();
	private ServiceDAO serviceDAO 		= new SQLServiceDAO();
	
	private DAOProvider() {
		
	}
	
	private static class ProviderHolder{
		static final DAOProvider INSTANCE = new DAOProvider();
	}
	
	public static DAOProvider getInstance() {
		return ProviderHolder.INSTANCE;
	}

	public AbonentDAO getAbonentDAO() {
		return abonentDAO;
	}

	public void setAbonentDAO(AbonentDAO abonentDAO) {
		this.abonentDAO = abonentDAO;
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
