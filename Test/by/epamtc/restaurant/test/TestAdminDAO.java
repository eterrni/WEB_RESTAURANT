package by.epamtc.restaurant.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;
import by.epamtc.restaurant.dao.impl.connection_poolTest.ConnectionPoolTest;
import by.epamtc.restaurant.dao.implTest.TestSQLAdminDAO;

class TestAdminDAO {

	private static final String SELECT_ORDER_BY_ID = "SELECT * FROM rest_db_test.order WHERE rest_db_test.order.id_order='1';";
	private static final ConnectionPoolTest connectionPool = ConnectionPoolTest.getInstance();
	private static final Integer ORDER_ID = 1;
	private static final String CONFIRMED_STATUS = "CONFIRMED";
	private static final Integer STATUS = 2;

	@Test
	void changeOrderStatus() throws DAOException, SQLException, ConnectionPoolException {

		TestSQLAdminDAO testSQLAdminDAO = new TestSQLAdminDAO();
		testSQLAdminDAO.changeOrderStatus(ORDER_ID);

		Connection cn = connectionPool.takeConnection();
		PreparedStatement ps = cn.prepareStatement(SELECT_ORDER_BY_ID);
		ResultSet rs = ps.executeQuery();

		String expectedStatus = CONFIRMED_STATUS;
		String actualStatus = null;
		while (rs.next()) {
			actualStatus = rs.getString(STATUS);
		}

		org.junit.Assert.assertEquals(expectedStatus, actualStatus);

	}

	@Test
	void successfulGeneratePayment() throws DAOException, ConnectionPoolException {

		TestSQLAdminDAO testSQLAdminDAO = new TestSQLAdminDAO();
		testSQLAdminDAO.generatePayment(ORDER_ID);

		Connection cn = connectionPool.takeConnection();

	}

	@Test
	void unsuccessfulGeneratePayment() throws DAOException {

	}

	@Test
	void successfulAppointUserAnAdministrator() throws DAOException {

	}

	@Test
	void unsuccessfulAppointUserAnAdministrator() throws DAOException {

	}

	@Test
	void successfulAppointAdministratorAnUser() throws DAOException {

	}

	@Test
	void unsuccessfulAppointAdministratorAnUser() throws DAOException {

	}

}
