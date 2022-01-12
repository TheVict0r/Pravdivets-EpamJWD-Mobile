package by.epamjwd.mobile.service.impl;

import java.util.Date;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.mail.MailCodeManager;
import by.epamjwd.mobile.service.validation.InputDataValidator;
import by.epamjwd.mobile.util.HashGenerator;

public class UserServiceImpl implements UserService {
	private final static long ERROR_ID = -1L;
	private final static long EMPTY_ID = 0L;
	
	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDao = provider.getUserDAO();

	@Override
	public Optional<User> findUserByLogin(String login) throws ServiceException {
		Optional<User>  user = Optional.empty();
		if (InputDataValidator.isEmail(login)) {
			user = findUserByEmail(login);
		} else if (InputDataValidator.isPhone(login)) {
			user = findUserByPhone(login);
		}
		return user;
	}
	
	@Override
	public Optional<User> findUserById(long id) throws ServiceException {
		Optional<User> user = Optional.empty();
		try {
			user = userDao.findUserById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		Optional<User> user = Optional.empty();
				
		if(InputDataValidator.isEmail(email)) {
		try {
			user = userDao.findUserByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		}
		return user;
	}

	@Override
	public Optional<User> findUserByPassport(String passport) throws ServiceException {
		Optional<User> user = Optional.empty();
		if(InputDataValidator.isPassport(passport)){
		try {
			user = userDao.findUserByPassport(passport);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		}
		return user;
	}
	
	@Override
	public Optional<User> findUserByPhone(String phone) throws ServiceException {
		Optional<User> user = Optional.empty();
		if (InputDataValidator.isPhone(phone)) {
			try {
				user = userDao.findUserByPhone(phone);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return user;
	}

	@Override
	public long addNewUser(User user) throws ServiceException {
		long userId = ERROR_ID;
		if (InputDataValidator.isUserValid(user)) {
			try {
				userId = userDao.addUser(user);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return userId;
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		if (InputDataValidator.isUserValid(user)) {
			try {
				userDao.updateUser(user);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public void updatePassword(User user, String password) throws ServiceException {
			String passwordHash = HashGenerator.generateHash(password);
			user.setPassword(passwordHash);
			updateUser(user);
	}
	
	@Override
	public void updatePassword(String phone, String password) throws ServiceException {
		Optional<User> userOptional = findUserByPhone(phone);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			String passwordHash = HashGenerator.generateHash(password);
			user.setPassword(passwordHash);
			updateUser(user);
		}
	}

	@Override
	public int sendCodeByMail(String phone) throws ServiceException {
		int result = 0;
		if(InputDataValidator.isPhone(phone)) {
			try {
				Optional<User> userOptional = userDao.findUserByPhone(phone);
				if(userOptional.isPresent()) {
					User user = userOptional.get();
					result = MailCodeManager.getInstance().sendGenereatedCodeByMail(user.getEmail());
				}
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}
	
	@Override
	public boolean isPasswordCorrect(User user, String password) {
		boolean result = false;
		if(password.isBlank() || password == null || user == null) {
			return false;
		}
		
		if (InputDataValidator.isUserValid(user)) {
			result = HashGenerator.generateHash(password).equals(user.getPassword());
		}
		return result;
	}

	@Override
	public boolean doesPhoneExist(String phone) throws ServiceException {
		return findUserByPhone(phone).isPresent();
	}	
	
	@Override
	public boolean isPasswordCorrect(String password) {
		return InputDataValidator.isPassword(password);
	}	
	
	@Override
	public boolean isSignupRequired(String phone) throws ServiceException {
		boolean result = false;
		Optional<User> userOptional = findUserByPhone(phone);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			result = user.getPassword() == null;
		}
		return result;
	}	

	@Override
	public boolean isEmailBooked(String email) throws ServiceException {
		Optional<User> user = findUserByEmail(email);
		return user.isPresent();
		
	}
	
	@Override
	public boolean isPassportBooked(String passport) throws ServiceException {
		Optional<User> user = findUserByPassport(passport);
		return user.isPresent();
	}
	
	@Override
	public User buildConsultant(String firstName, String middleName, String lastName, 
			String password, String passport, String email) {
		User user = null;
		user = new User(EMPTY_ID, HashGenerator.generateHash(password), firstName, middleName, lastName, 
						passport, email, Role.CONSULTANT);
		return user;
	}

	@Override
	public User buildUser(String firstName, String middleName, String lastName, 
			String passport, String email) {
		User user = null;
		user = new User(EMPTY_ID, null, firstName, middleName, lastName, 
						passport, email, Role.SUBSCRIBER);
		return user;
	}

	
	
	@Override
	public Subscriber buildSubscriber(String phone, long planId, long userId) throws ServiceException {
		Subscriber subscriber = null;
		ServiceProvider serviceProvider = ServiceProvider.getInstance();	
		PlanService planService = serviceProvider.getPlanService();
		Optional<Plan> planOptional = planService.findPlanByID(planId);
		if (planOptional.isPresent()) {
		Plan plan = planOptional.get();	
		
		int account = plan.getUpfrontPayment();
		
		subscriber = new Subscriber(EMPTY_ID, new Date(), account, phone, 
				new Date(), SubscriberStatus.ACTIVE, planId, userId);
		}
		return subscriber;
	}

	
	
}
