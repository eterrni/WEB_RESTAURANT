package by.epamtc.restaurant.service.impl;

import by.epamtc.restaurant.dao.UserDAO;

import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.service.exception.UserExistsServiceException;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.exception.UserExistsDAOException;
import by.epamtc.restaurant.bean.User;
import by.epamtc.restaurant.bean.UserAuthData;
import by.epamtc.restaurant.bean.UserRegistrationData;
import by.epamtc.restaurant.bean.UserUpdateData;
import by.epamtc.restaurant.dao.DAOFactory;

public class UserServiceImpl implements UserService {

	private static final DAOFactory instance = DAOFactory.getInstance();
	private static final UserDAO userDAO = instance.getUserDAO();

	@Override
	public User authorization(UserAuthData userAuthData) throws ServiceException {
		User user;

		try {
			user = userDAO.authorization(userAuthData);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public boolean registration(UserRegistrationData userRegistrationData)
			throws ServiceException, UserExistsServiceException {
		boolean registration = false;

		try {
			registration = userDAO.registartion(userRegistrationData);
		} catch (UserExistsDAOException e) {
			throw new UserExistsServiceException(e);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return registration;
	}

	@Override
	public boolean update(UserUpdateData userUpdateData, User user) throws ServiceException {
		boolean flag = false;

		try {
			if (userDAO.updateUserData(userUpdateData, user)) {
				flag = true;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

}
