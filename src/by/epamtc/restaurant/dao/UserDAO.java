package by.epamtc.restaurant.dao;

import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.bean.user.UserUpdateData;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.exception.UserExistsDAOException;

public interface UserDAO {
	User authorization(UserAuthData userAuthData) throws DAOException;

	boolean registartion(UserRegistrationData userRegistrationData) throws DAOException, UserExistsDAOException;

	boolean updateUserData(UserUpdateData userUpdateData, User user) throws DAOException;
}
