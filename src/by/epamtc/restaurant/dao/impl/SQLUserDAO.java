package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.restaurant.connection_pool.ConnectionPool;
import by.epamtc.restaurant.connection_pool.exception.ConnectionPoolException;
import by.epamtc.restaurant.dao.UserDAO;
import by.epamtc.restaurant.dao.exception.DAOException;

public class SQLUserDAO implements UserDAO {

	private static final String SELECT_LOGIN_PASSWORD = "SELECT * FROM `rest_db`.users\r\n"
			+ "WHERE users.login =? AND users.password=?;";
	private static final String ADD_NEW_USER = "INSERT INTO users(name, surname, patronymic, login, "
			+ "password, phone_number, age, email, users_role_id_role) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public void signIn(String login, String password) throws DAOException {

	}

	@Override
	public void registartion(String name, String surname, String patronymic, String login, String password,
			String phone_number, Integer age, String email, Integer role_id) throws DAOException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
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
			ps.setString(6, phone_number);
			ps.setInt(7, age);
			ps.setString(8, email);
			ps.setInt(9, role_id);

			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			connectionPool.returnConnection(cn);
			throw new DAOException("registartion", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					connectionPool.returnConnection(cn);
					throw new DAOException(e);
				}
			}
		}

		connectionPool.returnConnection(cn);
	}

	@Override
	public boolean userPresenceInSystem(String login, String password) throws DAOException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(SELECT_LOGIN_PASSWORD);
			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return false;
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("userPresenceInSystem", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}

			connectionPool.returnConnection(cn);
		}

		return true;
	}

}
