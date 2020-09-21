package by.epamtc.restaurant.dao;

import by.epamtc.restaurant.bean.User;
import by.epamtc.restaurant.dao.exception.DAOException;

public interface UserDAO {
	void signIn(User user) throws DAOException;

	void registartion(User user) throws DAOException;

	public boolean userPresenceInSystem(User user) throws DAOException;
}
