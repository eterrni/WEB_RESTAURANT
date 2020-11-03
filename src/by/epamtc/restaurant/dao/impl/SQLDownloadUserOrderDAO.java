package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.order.OrderStatus;
import by.epamtc.restaurant.bean.payment.Payment;
import by.epamtc.restaurant.bean.payment.PaymentStatus;
import by.epamtc.restaurant.dao.DownloadUserOrderDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;

public class SQLDownloadUserOrderDAO implements DownloadUserOrderDAO {

	private static final String SELECT_USER_ORDER = "SELECT * FROM rest_db.order WHERE users_id_users =?;";
	private static final String SELECT_USER_PAYMENT ="SELECT rest_db.payment.id_payment,rest_db.payment.amount,rest_db.payment.status,rest_db.payment.order_id_order\r\n" + 
			"FROM rest_db.payment join rest_db.order\r\n" + 
			"ON rest_db.payment.order_id_order = rest_db.order.id_order\r\n" + 
			"WHERE rest_db.order.users_id_users = ?;";
	private static final String MESSAGE_SQL_EXCEPTION = "SQLDownloadUserOrderDAO - SQLException";
	private static final String MESSAGE_CONNECTION_POOL_EXCEPTION = "SQLDownloadUserOrderDAO - SQLException";
	

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(SQLDownloadMenuDAO.class);

	@Override
	public List<Order> downloadUserOrderList(Integer userId) throws DAOException {

		List<Order> userOrderList = new ArrayList<>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(SELECT_USER_ORDER);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setId(Integer.parseInt(rs.getString(1)));
				order.setStatus(OrderStatus.valueOf(rs.getString(2)));
				order.setCreateDate(rs.getDate(3));
				userOrderList.add(order);
			}

		} catch (SQLException e) {
			logger.error(MESSAGE_SQL_EXCEPTION);
			throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			logger.error(MESSAGE_CONNECTION_POOL_EXCEPTION);
			throw new DAOException(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps, rs);
			} catch (ConnectionPoolException e) {
				logger.error(MESSAGE_CONNECTION_POOL_EXCEPTION);
				throw new DAOException(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
			}
		}

		return userOrderList;
	}

	@Override
	public List<Payment> downloadUserPaymentList(Integer userId) throws DAOException {
		List<Payment> userPaymentList = new ArrayList<>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(SELECT_USER_PAYMENT);
			ps.setInt(1, userId);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Payment payment = new Payment();
				payment.setId(rs.getInt(1));
				payment.setAmount(rs.getDouble(2));
				payment.setStatus(PaymentStatus.valueOf(rs.getString(3)));
				payment.setOrderId(rs.getInt(4));
				
				userPaymentList.add(payment);
			}
		} catch(SQLException e) {
			logger.error(MESSAGE_SQL_EXCEPTION);
			throw new DAOException(MESSAGE_SQL_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			logger.error(MESSAGE_CONNECTION_POOL_EXCEPTION);
			throw new DAOException(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps, rs);
			} catch (ConnectionPoolException e) {
				logger.error(MESSAGE_CONNECTION_POOL_EXCEPTION);
				throw new DAOException(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
			}
		}
		return userPaymentList;
	}

}
