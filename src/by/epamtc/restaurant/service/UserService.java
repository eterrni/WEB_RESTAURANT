package by.epamtc.restaurant.service;

import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.bean.user.UserUpdateData;
import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.service.exception.UserExistsServiceException;

public interface UserService {
	User authorization(UserAuthData userAuthData) throws ServiceException;
	boolean registration(UserRegistrationData userRegistrationData) throws ServiceException, UserExistsServiceException;
	boolean update(UserUpdateData userUpdateData, User user) throws ServiceException;
}
