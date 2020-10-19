package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.user.Role;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.bean.user.UserUpdateData;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;
import by.epamtc.restaurant.dao.UserDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.exception.UserExistsDAOException;

public class SQLUserDAO implements UserDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static final String SELECT_USER_LOGIN_PASSWORD = "SELECT * FROM `rest_db`.users\r\n"
			+ "WHERE users.login =? AND users.password=?;";
	private static final String ADD_NEW_USER = "INSERT INTO users(name, surname, patronymic, login, "
			+ "password, phone_number, age, email, users_role_id_role) VALUES(?, ?, ?, ?, ?, ?, ?, ?, 2)";
	private static final String UPDATE_USER_DATA = "UPDATE `rest_db`.`users` SET `name` = ?, `surname` = ?, `patronymic` =?, `phone_number` = ?, `age` = ?, `email` = ? WHERE (`id_users` = ?);";
	private static final String ROLE_ID_ADMINISTRATOR = "1";

	private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public User authorization(UserAuthData userAuthData) throws DAOException {

		User user = null;

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(SELECT_USER_LOGIN_PASSWORD);

			ps.setString(1, userAuthData.getLogin());
			ps.setString(2, userAuthData.getPassword());

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			} else {

				user = new User();

				Integer id = Integer.parseInt(rs.getString(1));
				String name = rs.getString(2);
				String surname = rs.getString(3);
				String patronymic = rs.getString(4);
				String phoneNumber = rs.getString(7);
				Integer age = Integer.parseInt(rs.getString(8));
				String email = rs.getString(9);
				String role = rs.getString(10);

				user.setId(id);
				user.setName(name);
				user.setSurname(surname);
				user.setPatronymic(patronymic);
				user.setPhoneNumber(phoneNumber);
				user.setAge(age);
				user.setEmail(email);
				if (role.equals(ROLE_ID_ADMINISTRATOR)) {
					user.setRole(Role.ADMINISTRATOR);
				} else
					user.setRole(Role.USER);
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Authorization exception", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException("Error close connection", e);
			}
		}

		return user;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean registartion(UserRegistrationData userRegistrationData) throws DAOException, UserExistsDAOException {

		boolean registration = false;

		String name = userRegistrationData.getName();
		String surname = userRegistrationData.getSurname();
		String patronymic = userRegistrationData.getPatronymic();
		String login = userRegistrationData.getLogin();
		String password = userRegistrationData.getPassword();
		String phoneNumber = userRegistrationData.getPhoneNumber();
		Integer age = userRegistrationData.getAge();
		String email = userRegistrationData.getEmail();

		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(ADD_NEW_USER);

			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, patronymic);
			ps.setString(4, login);
			ps.setString(5, password);
			ps.setString(6, phoneNumber);
			ps.setInt(7, age);
			ps.setString(8, email);

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new UserExistsDAOException("user_exist", e);
		} catch (SQLException e) {
			throw new DAOException("Registration SQLException", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Registration ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				throw new DAOException("close_connectionPool_exception", e);
			}
		}
		return registration;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean updateUserData(UserUpdateData userUpdateData, User user) throws DAOException {

		Connection cn = null;
		PreparedStatement ps = null;

		String name = userUpdateData.getName();
		String surname = userUpdateData.getSurname();
		String patronymic = userUpdateData.getPatronymic();
		String phoneNumber = userUpdateData.getPhoneNumber();
		Integer age = userUpdateData.getAge();
		String email = userUpdateData.getEmail();
		Integer id = userUpdateData.getId();

		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(UPDATE_USER_DATA);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, patronymic);
			ps.setString(4, phoneNumber);
			ps.setInt(5, age);
			ps.setString(6, email);
			ps.setInt(7, id);

			if (ps.executeUpdate() == 1) {
				user.setName(name);
				user.setSurname(surname);
				user.setPatronymic(patronymic);
				user.setPhoneNumber(phoneNumber);
				user.setAge(age);
				user.setEmail(email);
				return true;
			}

		} catch (ConnectionPoolException e) {
			logger.error("update - connection pool exception");
			throw new DAOException("update - connection pool exception", e);

		} catch (SQLException e) {
			logger.error("update - SQLException");
			throw new DAOException("update - SQLException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				throw new DAOException("close_connectionPool_exception", e);
			}
		}
		return false;
	}
}
