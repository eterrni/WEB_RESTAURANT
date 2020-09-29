package by.epamtc.restaurant.dao;

import by.epamtc.restaurant.bean.User;
import by.epamtc.restaurant.bean.UserAuthData;
import by.epamtc.restaurant.bean.UserRegistrationData;
import by.epamtc.restaurant.bean.UserUpdateData;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.exception.UserExistsDAOException;

public interface UserDAO {
	User authorization(UserAuthData userAuthData) throws DAOException;

	boolean registartion(UserRegistrationData userRegistrationData) throws DAOException, UserExistsDAOException;

	boolean updateUserData(UserUpdateData userUpdateData, User user) throws DAOException;
}
