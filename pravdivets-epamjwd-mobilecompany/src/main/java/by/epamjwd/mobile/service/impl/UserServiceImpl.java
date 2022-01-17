package by.epamjwd.mobile.service.impl;

import java.util.Optional;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.repository.IDRepository;
import by.epamjwd.mobile.repository.IndexRepository;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.mail.MailCodeManager;
import by.epamjwd.mobile.service.validation.InputDataValidator;
import by.epamjwd.mobile.util.HashGenerator;

public class UserServiceImpl implements UserService {
	UserDAO userDao = DAOProvider.getInstance().getUserDAO();

	
	
	/**
	 * Provides User retrieved by it's login. 
	 * The login can be an e-mail address or a phone number ass well.
	 * 
	 * @param login - user's login
	 * @return User as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting User from the data storage
	 */
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
	
	
	/**
	 * Provides User retrieved by it's ID.
	 * 
	 * @param id - user's ID 
	 * @return User as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting User from the data storage
	 */
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

	/**
	 * Provides User retrieved by it's e-mail. 
	 * 
	 * @param e-mail - user's e-mail
	 * @return User as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting User from the data storage
	 */
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

	/**
	 * Provides User retrieved by it's passport number. 
	 * 
	 * @param passport - user's passport number
	 * @return User as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting User from the data storage
	 */
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

	/**
	 * Provides User retrieved by it's phone number. 
	 * 
	 * @param phone - user's phone number
	 * @return User as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting User from the data storage
	 */
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

	/**
	 * Adds the User to the data storage.
	 * 
	 * @param User - new User
	 * @return new User's ID
	 * @throws ServiceException in the case when DaoException occurs while adding a User 
	 * to the data storage
	 */
	@Override
	public long addUser(User user) throws ServiceException {
		long userId = IDRepository.EMPTY_ID;
		if (InputDataValidator.isUserValid(user)) {
			try {
				userId = userDao.addUser(user);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return userId;
	}

	/**
	 * Updates User's data.
	 * 
	 * @param user - User
	 * @throws ServiceException in the case when DaoException occurs while updating the User 
	 */
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

	/**
	 * Updates User's password. Because of the security reasons the hash-code 
	 * of the new password will be added to the data storage, not the raw password.
	 * 
	 * @param user - User
	 * @param passport - user's password
	 * @throws ServiceException in the case when DaoException occurs while updating the User 
	 */
	@Override
	public void updatePassword(User user, String password) throws ServiceException {
			String passwordHash = HashGenerator.generateHash(password);
			user.setPassword(passwordHash);
			updateUser(user);
	}
	
	/**
	 * Updates User's password. The user will be fount by it's phone number.
	 * Because of the security reasons the hash-code of the new password will be 
	 * added to the data storage, not the raw password.
	 * 
	 * @param phone - user's phone number
	 * @param password - user's password
	 * @throws ServiceException in the case when DaoException occurs while updating the User 
	 */
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

	
	/**
	 * For Subscriber Users only. Send the authentication code to Subscriber user's e-mail. 
	 * This e-mail will be found by subscriber's phone number.
	 * 
	 * @param phone - user's phone number
	 * @return authentication code, that was sent to the user's e-mail
	 * @throws ServiceException in the case when DaoException occurs 
	 * while searching the User in the data storage
	 */
	@Override
	public int sendCodeToUserByMail(String phone) throws ServiceException {
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
	
	/**
	 * Checks if the provided password is equal to user's password.
	 * (Actually the method compares not the raw passwords, but their hash codes)
	 * 
	 * @param user - current User
	 * @param password - user's password
	 * @return true if passwords are equal
	 */
	@Override
	public boolean isPasswordCorrect(User user, String password) {
		boolean result = false;
		if (password.isBlank() || password == null || user == null) {
			return false;
		}

		if (InputDataValidator.isUserValid(user)) {
			result = HashGenerator.generateHash(password).equals(user.getPassword());
		}
		return result;
	}
	
	/**
	 * Checks for the presence of a e-mail in the data storage.
	 * 
	 * @param email - user's email
	 * @throws ServiceException in the case when DaoException occurs 
	 * while searching the User in the data storage
	 */
	@Override
	public boolean isEmailBooked(String email) throws ServiceException {
		boolean result = false;
		if (InputDataValidator.isEmail(email)) {
			Optional<User> user = findUserByEmail(email);
			result = user.isPresent();
		}

		return result;
	}
	
	/**
	 * Checks for the presence of a passport number in the data storage.
	 * 
	 * @param passport - user's passport number
	 * @throws ServiceException in the case when DaoException occurs 
	 * while searching the User in the data storage
	 */
	@Override
	public boolean isPassportBooked(String passport) throws ServiceException {
		Optional<User> user = findUserByPassport(passport);
		return user.isPresent();
	}
	
	/**
	 * Builds new User, which is also a subscriber, with empty user ID.
	 * 
	 * @param firstName - user's first name
	 * @param middleName - user's middle name
	 * @param lastName - user's last name
	 * @param passport - user's passport number
	 * @param email - user's user's e-mail
	 * @return new User
	 */
	@Override
	public User buildSubscriberUser(String firstName, String middleName, String lastName, 
			String passport, String email) {
		User user = null;
		user = new User(IDRepository.EMPTY_ID, null, firstName, middleName, lastName, 
						passport, email, Role.SUBSCRIBER);
		return user;
	}

	/**
	 * Builds new User, which is also a consultant, with empty user ID.
	 * 
	 * @param firstName - user's first name
	 * @param middleName - user's middle name
	 * @param lastName - user's last name
	 * @param password - user's password
	 * @param passport - user's passport number
	 * @param email - user's e-mail
	 * @return new User
	 */
	@Override
	public User buildConsultantUser(String firstName, String middleName, String lastName, 
			String password, String passport, String email) {
		User user = null;
		user = new User(IDRepository.EMPTY_ID, HashGenerator.generateHash(password), firstName, middleName, lastName, 
						passport, email, Role.CONSULTANT);
		return user;
	}

}
