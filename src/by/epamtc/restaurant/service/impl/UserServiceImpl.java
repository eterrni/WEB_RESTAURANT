package by.epamtc.restaurant.service.impl;

import by.epamtc.restaurant.dao.UserDAO;
import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.service.exception.UserExistsServiceException;
import by.epamtc.restaurant.service.validator.UserValidator;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.exception.UserExistsDAOException;

import java.security.NoSuchAlgorithmException;

import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.bean.user.UserUpdateData;
import by.epamtc.restaurant.dao.DAOFactory;

public class UserServiceImpl implements UserService {

	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final UserDAO userDAO = factory.getUserDAO();
	private static final UserValidator validator = UserValidator.getInstance();

	@Override
	public User authorization(UserAuthData userAuthData) throws ServiceException {
		User user;
		if (!validator.loginValidator(userAuthData)) {
			return null;
		}
		try {

			String password = Cryptographer.encode(userAuthData.getPassword());
			userAuthData.setPassword(password);

			user = userDAO.authorization(userAuthData);
		} catch (DAOException | NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public boolean registration(UserRegistrationData userRegistrationData)
			throws ServiceException, UserExistsServiceException {
		boolean registration = false;
		if (!validator.registartionValidator(userRegistrationData)) {
			return false;
		}
		try {

			String password = Cryptographer.encode(userRegistrationData.getPassword());
			userRegistrationData.setPassword(password);

			registration = userDAO.registartion(userRegistrationData);
		} catch (UserExistsDAOException e) {
			throw new UserExistsServiceException(e);
		} catch (DAOException | NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}
		return registration;
	}

	@Override
	public boolean update(UserUpdateData userUpdateData, User user) throws ServiceException {
		boolean flag = false;
		if (!validator.updateDataValidator(userUpdateData)) {
			return false;
		}
		try {
			if (userDAO.updateUserData(userUpdateData, user)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

}
