package by.epamtc.restaurant.service.impl;

import by.epamtc.restaurant.dao.UserDAO;

import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.service.exception.UserExistsServiceException;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.exception.UserExistsDAOException;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.bean.user.UserUpdateData;
import by.epamtc.restaurant.controller.validator.UserValidator;
import by.epamtc.restaurant.dao.DAOFactory;

public class UserServiceImpl implements UserService {

	private static final DAOFactory instance = DAOFactory.getInstance();
	private static final UserDAO userDAO = instance.getUserDAO();
	private static final UserValidator validator = UserValidator.getInstance();

	@Override
	public User authorization(UserAuthData userAuthData) throws ServiceException {
		User user;
		if (validator.loginValidator(userAuthData)) {
			try {
				user = userDAO.authorization(userAuthData);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			user = null;
		}

		return user;
	}

	@Override
	public boolean registration(UserRegistrationData userRegistrationData)
			throws ServiceException, UserExistsServiceException {
		boolean registration = false;
		if (validator.registartionValidator(userRegistrationData)) {
			try {
				registration = userDAO.registartion(userRegistrationData);
			} catch (UserExistsDAOException e) {
				throw new UserExistsServiceException(e);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			registration = false;
		}
		return registration;
	}

	@Override
	public boolean update(UserUpdateData userUpdateData, User user) throws ServiceException {
		boolean flag = false;
		if (validator.updateDataValidator(userUpdateData)) {
			try {
				if (userDAO.updateUserData(userUpdateData, user)) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			flag = false;
		}
		return flag;
	}

}
