package by.epamtc.restaurant.dao;

import by.epamtc.restaurant.dao.exception.DAOException;

public interface UserDAO {
	void signIn(String login, String password) throws DAOException;
	void registartion(String name, String surname, String patronymic, String login, String password, String phone_number, Integer age, String email, Integer role_id) throws DAOException;
	public boolean userPresenceInSystem(String login, String password) throws DAOException;
}
