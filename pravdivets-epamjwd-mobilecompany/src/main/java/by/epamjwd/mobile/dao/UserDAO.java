package by.epamjwd.mobile.dao;

import by.epamjwd.mobile.bean.Abonent;

public interface UserDAO {
	
	boolean authorization (String login, String passwordHash);
	void registration(Abonent newAbonent);
	Abonent lookAbonent(int idAbonent);
	void activateService(int serviceId);
	void deActivateService(int serviceId);
	void switchServiceParameters(String oldServiceId, String newServiceId);
	void changePhoneNumber(String oldPhoneNumber, String newPhoneNumber);
	void pause();
	
}
