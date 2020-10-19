package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.bean.order.OrderStatus;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;

public class SQLPlaceOrderDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static final String ADD_ORDER = "INSERT INTO `rest_db`.`order` (`status`, `users_id_users`) VALUES (?, ?);";
	private static final String GET_ORDER_ID = "SELECT id_order FROM rest_db.order where status ='NOT_CONFIRMED' AND users_id_users = ?;";

	private static final String INSERT_DISH_TO_ORDER = "REPLACE INTO `rest_db`.`order_has_dishes` (`order_id_order`, `dishes_id_dishes`, `count`) VALUES (?, ?, ?);";
	private static final String INSERT_DRINK_TO_ORDER = "REPLACE INTO `rest_db`.`order_has_drinks` (`order_id_order`, `drinks_id_drinks`, `count`) VALUES (?, ?, ?);";
	private static final String INSERT_DESERT_TO_ORDER = "REPLACE INTO `rest_db`.`order_has_deserts` (`order_id_order`, `deserts_id_deserts`, `count`) VALUES (?, ?, ?);";

	private static final Logger logger = LogManager.getLogger(SQLPlaceOrderDAO.class);

	public Integer placeOrder(Integer userId) throws DAOException {

		Connection cn = null;
		PreparedStatement firstPs = null;
		PreparedStatement secondPs = null;
		ResultSet rs = null;
		Integer orderId = null;

		String orderStatus = OrderStatus.NOT_CONFIRMED.toString();

		try {

			cn = connectionPool.takeConnection();
			firstPs = cn.prepareStatement(ADD_ORDER);
			firstPs.setString(1, orderStatus);
			firstPs.setInt(2, userId);

			firstPs.execute();

			secondPs = cn.prepareStatement(GET_ORDER_ID);
			secondPs.setInt(1, userId);
			rs = secondPs.executeQuery();

			while (rs.next()) {
				orderId = Integer.parseInt(rs.getString(1));
			}

		} catch (SQLException e) {
			logger.error("SQLPlaceOrderDAO( placeOrder() )- SQLException");
			throw new DAOException("SQLPlaceOrderDAO( placeOrder() ) - SQLException", e);

		} catch (ConnectionPoolException e) {
			logger.error("SQLPlaceOrderDAO( placeOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLPlaceOrderDAO( placeOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, firstPs, rs);
				if (secondPs != null) {
					try {
						secondPs.close();
					} catch (SQLException e) {
						logger.error("SQLPlaceOrderDAO( placeOrder() ) - error close secondPs connection");
						throw new DAOException("SQLPlaceOrderDAO( placeOrder() ) - error close secondPs connection", e);
					}
				}
			} catch (ConnectionPoolException e) {
				logger.error("SQLPlaceOrderDAO( placeOrder() ) - error close connection");
				throw new DAOException("SQLPlaceOrderDAO( placeOrder() ) - error close connection", e);
			}
		}

		return orderId;
	}

	public void enterDishFromOrder(List<Goods> orderList, Integer orderId, Map<String, Integer> dishMap)
			throws DAOException {

		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(INSERT_DISH_TO_ORDER);
			for (Map.Entry<String, Integer> entry : dishMap.entrySet()) {
				for (Goods good : orderList) {
					if (entry.getKey().equals(good.getName())) {
						ps.setInt(1, orderId);
						ps.setInt(2, good.getId());
						ps.setInt(3, entry.getValue());

						ps.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLPlaceOrderDAO( enterDishFromOrder() ) - SQLException");
			throw new DAOException("SQLPlaceOrderDAO( enterDishFromOrder() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLPlaceOrderDAO( enterDishFromOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLPlaceOrderDAO( enterDishFromOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLPlaceOrderDAO( enterDishFromOrder() ) - error close connection");
				throw new DAOException("SQLPlaceOrderDAO( enterDishFromOrder() ) - error close connection", e);
			}
		}
	}

	public void enterDrinkFromOrder(List<Goods> orderList, Integer orderId, Map<String, Integer> drinkMap)
			throws DAOException {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(INSERT_DRINK_TO_ORDER);
			for (Map.Entry<String, Integer> entry : drinkMap.entrySet()) {
				for (Goods good : orderList) {
					if (entry.getKey().equals(good.getName())) {
						ps.setInt(1, orderId);
						ps.setInt(2, good.getId());
						ps.setInt(3, entry.getValue());

						ps.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {
			logger.error("SQLPlaceOrderDAO( enterDrinkFromOrder() ) - SQLException");
			throw new DAOException("SQLPlaceOrderDAO( enterDrinkFromOrder() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLPlaceOrderDAO( enterDrinkFromOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLPlaceOrderDAO( enterDrinkFromOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLPlaceOrderDAO( enterDrinkFromOrder() ) - error close connection");
				throw new DAOException("SQLPlaceOrderDAO( enterDrinkFromOrder() ) - error close connection", e);
			}
		}
	}

	public void enterDesertFromOrder(List<Goods> orderList, Integer orderId, Map<String, Integer> desertMap)
			throws DAOException {
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(INSERT_DESERT_TO_ORDER);
			for (Map.Entry<String, Integer> entry : desertMap.entrySet()) {
				for (Goods good : orderList) {
					if (entry.getKey().equals(good.getName())) {
						ps.setInt(1, orderId);
						ps.setInt(2, good.getId());
						ps.setInt(3, entry.getValue());

						ps.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {
			logger.error("SQLPlaceOrderDAO( enterDesertFromOrder() ) - SQLException");
			throw new DAOException("SQLPlaceOrderDAO( enterDesertFromOrder() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLPlaceOrderDAO( enterDesertFromOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLPlaceOrderDAO( enterDesertFromOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLPlaceOrderDAO( enterDesertFromOrder() ) - error close connection");
				throw new DAOException("SQLPlaceOrderDAO( enterDesertFromOrder() ) - error close connection", e);
			}
		}
	}
}
