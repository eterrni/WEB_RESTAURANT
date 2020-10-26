package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.dao.AdminDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;

public class SQLAdminDAO implements AdminDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static final String CHANGE_ORDER_STATUS = "UPDATE `rest_db`.`order` SET `status` = 'CONFIRMED' WHERE (`id_order` = ?);";
	private static final String CHANGE_USER_STATUS = "UPDATE `rest_db`.`users` SET `users_role_id_role` = '1' WHERE (`id_users` = ?);";
	private static final String CHANGE_EMPLOYEE_STATUS = "UPDATE `rest_db`.`users` SET `users_role_id_role` = '2' WHERE (`id_users` = ?);";
	
	private static final Logger logger = LogManager.getLogger(SQLAdminDAO.class);

	@Override
	public void changeOrderStatus(Integer orderId) throws DAOException {
		
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(CHANGE_ORDER_STATUS);
			
			ps.setInt(1, orderId);
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("SQLAdminDAO (changeOrderStatus) - SQLException");
			throw new DAOException("SQLAdminDAO (changeOrderStatus) - SQLException", e);
		} catch(ConnectionPoolException e) {
			logger.error("SQLAdminDAO (changeOrderStatus) - connection pool exception");
			throw new DAOException("SQLAdminDAO (changeOrderStatus) - connection pool exception", e);
		}finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLAdminDAO (changeOrderStatus) - connection pool exception");
				throw new DAOException("SQLAdminDAO (changeOrderStatus) - connection pool exception", e);
			}
		}
	}

	@Override
	public void appointUserAnAdministrator(Integer userId) throws DAOException {
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(CHANGE_USER_STATUS);
			
			ps.setInt(1, userId);
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("SQLAdminDAO (appointUserAnAdministrator) - SQLException");
			throw new DAOException("SQLAdminDAO (appointUserAnAdministrator) - SQLException", e);
		} catch(ConnectionPoolException e) {
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

	@Override
	public void appointAdministratorAnUser(Integer employeeId) throws DAOException {
		Connection cn = null;
		PreparedStatement ps = null;
		
		try {
			
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(CHANGE_EMPLOYEE_STATUS);
			
			ps.setInt(1, employeeId);
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("SQLAdminDAO (appointAdministratorAnUser) - SQLException");
			throw new DAOException("SQLAdminDAO (appointAdministratorAnUser) - SQLException", e);
		} catch(ConnectionPoolException e) {
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
