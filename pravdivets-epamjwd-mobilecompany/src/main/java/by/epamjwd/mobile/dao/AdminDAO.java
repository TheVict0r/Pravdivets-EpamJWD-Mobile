package by.epamjwd.mobile.dao;

public interface AdminDAO extends UserDAO{

	void blockAbonent(int abonentId);
	void blockAbonentsGroup(int [] abonentId);
	
	void deactivateAbonent(int abonentId);
	void deactivateAbonentsGroup(int [] abonentId);

	
}
