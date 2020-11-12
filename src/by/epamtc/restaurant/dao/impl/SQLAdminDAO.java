package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.dao.AdminDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;

public class SQLAdminDAO implements AdminDAO {

	private static final String CHANGE_ORDER_STATUS = "UPDATE `rest_db`.`order` SET `status` = 'CONFIRMED' WHERE (`id_order` = ?);";
	private static final String CHANGE_USER_STATUS = "UPDATE `rest_db`.`users` SET `users_role_id_role` = '1' WHERE (`id_users` = ?);";
	private static final String CHANGE_EMPLOYEE_STATUS = "UPDATE `rest_db`.`users` SET `users_role_id_role` = '2' WHERE (`id_users` = ?);";

	private static final String DISHES_IN_ORDER = "SELECT rest_db.order_has_dishes.count, rest_db.dishes.price\r\n"
			+ "FROM rest_db.order JOIN rest_db.order_has_dishes\r\n"
			+ "ON rest_db.order.id_order = rest_db.order_has_dishes.order_id_order\r\n" + "join rest_db.dishes\r\n"
			+ "on rest_db.order_has_dishes.dishes_id_dishes = rest_db.dishes.id_dishes\r\n" + "where id_order = ?;";
	private static final String DRINKS_IN_ORDER = "SELECT rest_db.order_has_drinks.count, rest_db.drinks.price\r\n"
			+ "FROM rest_db.order JOIN rest_db.order_has_drinks\r\n"
			+ "ON rest_db.order.id_order = rest_db.order_has_drinks.order_id_order\r\n" + "join rest_db.drinks\r\n"
			+ "on rest_db.order_has_drinks.drinks_id_drinks = rest_db.drinks.id_drinks\r\n" + "where id_order = ?;";
	private static final String DESERTS_IN_ORDER = "SELECT rest_db.order_has_deserts.count, rest_db.deserts.price\r\n"
			+ "FROM rest_db.order JOIN rest_db.order_has_deserts\r\n"
			+ "ON rest_db.order.id_order = rest_db.order_has_deserts.order_id_order\r\n" + "join rest_db.deserts\r\n"
			+ "on rest_db.order_has_deserts.deserts_id_deserts = rest_db.deserts.id_deserts\r\n"
			+ "where id_order = ?;";
	private static final String INSERT_PAYMENT = "INSERT INTO `rest_db`.`payment` (`status`, `amount`, `order_id_order`) VALUES ('UNPAID', ?, ?);";

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(SQLAdminDAO.class);

	/**
	 * Execute the SQL statement and change order status.
	 * 
	 * @param id of the order to change its status for
	 * @exception DAOException if occurred severe problem with database
	 */
	@Override
	public void changeOrderStatus(Integer orderId) throws DAOException {

		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(CHANGE_ORDER_STATUS);

			ps.setInt(1, orderId);

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLAdminDAO (changeOrderStatus) - SQLException");
			throw new DAOException("SQLAdminDAO (changeOrderStatus) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLAdminDAO (changeOrderStatus) - connection pool exception");
			throw new DAOException("SQLAdminDAO (changeOrderStatus) - connection pool exception", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLAdminDAO (changeOrderStatus) - connection pool exception");
				throw new DAOException("SQLAdminDAO (changeOrderStatus) - connection pool exception", e);
			}
		}
	}

	/**
	 * First, requests are made to calculate the total payment amount. Then a
	 * request is made to generate the payment
	 * 
	 * @param ID of the order that the payment is based on
	 * @exception DAOException if occurred severe problem with database
	 */
	@SuppressWarnings("resource")
	@Override
	public void generatePayment(Integer orderId) throws DAOException {

		Double orderAmount = (double) 0;

		Connection cn = null;
		PreparedStatement firstPs = null;
		PreparedStatement secondPs = null;
		PreparedStatement thirdPs = null;
		PreparedStatement fourthPs = null;
		ResultSet rs = null;

		try {

			cn = connectionPool.takeConnection();
			firstPs = cn.prepareStatement(DISHES_IN_ORDER);
			firstPs.setInt(1, orderId);
			rs = firstPs.executeQuery();

			while (rs.next()) {
				Integer countDish = rs.getInt(1);
				Double priceDish = rs.getDouble(2);

				orderAmount += countDish * priceDish;
			}

			secondPs = cn.prepareStatement(DRINKS_IN_ORDER);
			secondPs.setInt(1, orderId);
			rs = secondPs.executeQuery();

			while (rs.next()) {
				Integer countDrink = rs.getInt(1);
				Double priceDrink = rs.getDouble(2);

				orderAmount += countDrink * priceDrink;
			}

			thirdPs = cn.prepareStatement(DESERTS_IN_ORDER);
			thirdPs.setInt(1, orderId);
			rs = thirdPs.executeQuery();

			while (rs.next()) {
				Integer countDesert = rs.getInt(1);
				Double priceDesert = rs.getDouble(2);

				orderAmount += countDesert * priceDesert;
			}

			fourthPs = cn.prepareStatement(INSERT_PAYMENT);
			fourthPs.setDouble(1, orderAmount);
			fourthPs.setInt(2, orderId);
			fourthPs.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLAdminDAO( generatePayment() )- SQLException");
			throw new DAOException("SQLAdminDAO( generatePayment() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLAdminDAO( generatePayment() ) - ConnectionPoolException");
			throw new DAOException("SQLAdminDAO( generatePayment() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, firstPs, rs);

				if (secondPs != null) {
					try {
						secondPs.close();
					} catch (SQLException e) {
						logger.error("SQLAdminDAO(generatePayment()) - error close secondPs connection");
						throw new DAOException("SQLAdminDAO( generatePayment() ) - error close secondPs connection", e);
					}
				}

				if (thirdPs != null) {
					try {
						thirdPs.close();
					} catch (SQLException e) {
						logger.error("SQLAdminDAO(generatePayment())-error close thirdPs connection");
						throw new DAOException("SQLAdminDAO(generatePayment())-error close thirdPs connection", e);
					}
				}

				if (fourthPs != null) {
					try {
						fourthPs.close();
					} catch (SQLException e) {
						logger.error("SQLAdminDAO(generatePayment())-error close fourthPs connection");
						throw new DAOException("SQLAdminDAO(generatePayment())-error close fourthPs connection", e);
					}
				}

			} catch (ConnectionPoolException e) {
				logger.error("SQLAdminDAO( generatePayment() ) - error close connection");
				throw new DAOException("SQLAdminDAO( generatePayment() ) - error close connection", e);
			}
		}

	}

	/**
	 * A request is made to change the status from USER to ADMINISTRATOR.
	 * 
	 * @param ID of the user whose status should be changed to administrator
	 * @exception DAOException if occurred severe problem with database
	 */
	@Override
	public void appointUserAnAdministrator(Integer userId) throws DAOException {
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(CHANGE_USER_STATUS);

			ps.setInt(1, userId);

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLAdminDAO (appointUserAnAdministrator) - SQLException");
			throw new DAOException("SQLAdminDAO (appointUserAnAdministrator) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLAdminDAO (appointUserAnAdministrator) - connection pool exception");
			throw new DAOException("SQLAdminDAO (appointUserAnAdministrator) - connection pool exception", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLAdminDAO (appointUserAnAdministrator) - connection pool exception");
				throw new DAOException("SQLAdminDAO (appointUserAnAdministrator) - connection pool exception", e);
			}
		}

	}

	/**
	 * A request is made to change the status from ADMINISTRATOR to USER.
	 * 
	 * @param ID of the administrator whose status should be changed to user
	 * @exception DAOException if occurred severe problem with database
	 */
	@Override
	public void appointAdministratorAnUser(Integer employeeId) throws DAOException {
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(CHANGE_EMPLOYEE_STATUS);

			ps.setInt(1, employeeId);

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQLAdminDAO (appointAdministratorAnUser) - SQLException");
			throw new DAOException("SQLAdminDAO (appointAdministratorAnUser) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLAdminDAO (appointAdministratorAnUser) - connection pool exception");
			throw new DAOException("SQLAdminDAO (appointAdministratorAnUser) - connection pool exception", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLAdminDAO (appointAdministratorAnUser) - connection pool exception");
				throw new DAOException("SQLAdminDAO (appointAdministratorAnUser) - connection pool exception", e);
			}
		}

	}

}
