package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.bean.order.OrderStatus;
import by.epamtc.restaurant.dao.UserFeaturesDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;

public class SQLUserFeaturesDAO implements UserFeaturesDAO {

	private static final String ADD_ORDER = "INSERT INTO `rest_db`.`order` (`status`, `users_id_users`) VALUES (?, ?);";
	private static final String GET_ORDER_ID = "SELECT id_order FROM rest_db.order where status ='NOT_CONFIRMED' AND users_id_users = ?;";

	private static final String INSERT_DISH_TO_ORDER = "REPLACE INTO `rest_db`.`order_has_dishes` (`order_id_order`, `dishes_id_dishes`, `count`) VALUES (?, ?, ?);";
	private static final String INSERT_DRINK_TO_ORDER = "REPLACE INTO `rest_db`.`order_has_drinks` (`order_id_order`, `drinks_id_drinks`, `count`) VALUES (?, ?, ?);";
	private static final String INSERT_DESERT_TO_ORDER = "REPLACE INTO `rest_db`.`order_has_deserts` (`order_id_order`, `deserts_id_deserts`, `count`) VALUES (?, ?, ?);";

	private static final String GET_DISH_FROM_ORDER = "SELECT rest_db.dishes.name, rest_db.order_has_dishes.count, rest_db.dishes.price\r\n"
			+ "FROM rest_db.order JOIN rest_db.order_has_dishes JOIN rest_db.dishes\r\n"
			+ "ON rest_db.order.id_order = order_id_order\r\n" + "where rest_db.order.id_order =? \r\n"
			+ "AND rest_db.order_has_dishes.dishes_id_dishes = rest_db.dishes.id_dishes;\r\n" + "";

	private static final String GET_DRINK_FROM_ORDER = "SELECT rest_db.drinks.name, rest_db.order_has_drinks.count, rest_db.drinks.price\r\n"
			+ "FROM rest_db.order JOIN rest_db.order_has_drinks JOIN rest_db.drinks\r\n"
			+ "ON rest_db.order.id_order = order_id_order\r\n" + "where rest_db.order.id_order =? \r\n"
			+ "AND rest_db.order_has_drinks.drinks_id_drinks = rest_db.drinks.id_drinks;\r\n" + "";

	private static final String GET_DESERT_FROM_ORDER = "SELECT rest_db.deserts.name, rest_db.order_has_deserts.count, rest_db.deserts.price\r\n"
			+ "FROM rest_db.order JOIN rest_db.order_has_deserts JOIN rest_db.deserts\r\n"
			+ "ON rest_db.order.id_order = order_id_order\r\n" + "where rest_db.order.id_order =? \r\n"
			+ "AND rest_db.order_has_deserts.deserts_id_deserts = rest_db.deserts.id_deserts;\r\n" + "";

	private static final String PAY_ORDER = "UPDATE `rest_db`.`payment` SET `status` = 'PAID' WHERE (`id_payment` = ?);";

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(SQLUserFeaturesDAO.class);

	@Override
	public void payOrder(Integer paymentId) throws DAOException {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = connectionPool.takeConnection();
			ps = cn.prepareStatement(PAY_ORDER);
			ps.setInt(1, paymentId);

			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLUserFeaturesDAO( payOrder() )- SQLException");
			throw new DAOException("SQLUserFeaturesDAO( payOrder() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLUserFeaturesDAO( payOrder() )- SQLException");
			throw new DAOException("SQLUserFeaturesDAO( payOrder() ) - SQLException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLUserFeaturesDAO( payOrder() )- SQLException");
				throw new DAOException("SQLUserFeaturesDAO( payOrder() ) - SQLException", e);
			}
		}

	}

	@SuppressWarnings({ "resource" })
	@Override
	public List<Goods> getOrderDetailList(Integer orderId) throws DAOException {

		List<Goods> orderDetailList = new ArrayList<>();

		Connection cn = null;
		PreparedStatement firstPs = null;
		PreparedStatement secondPs = null;
		PreparedStatement thirdPs = null;
		ResultSet rs = null;

		try {

			cn = connectionPool.takeConnection();
			firstPs = cn.prepareStatement(GET_DISH_FROM_ORDER);
			firstPs.setInt(1, orderId);
			rs = firstPs.executeQuery();

			while (rs.next()) {
				Goods good = new Goods();
				good.setName(rs.getString(1));
				good.setCount(rs.getInt(2));
				good.setPrice(rs.getDouble(3));

				orderDetailList.add(good);
			}

			secondPs = cn.prepareStatement(GET_DRINK_FROM_ORDER);
			secondPs.setInt(1, orderId);
			rs = secondPs.executeQuery();

			while (rs.next()) {
				Goods good = new Goods();
				good.setName(rs.getString(1));
				good.setCount(rs.getInt(2));
				good.setPrice(rs.getDouble(3));

				orderDetailList.add(good);
			}

			thirdPs = cn.prepareStatement(GET_DESERT_FROM_ORDER);
			thirdPs.setInt(1, orderId);
			rs = thirdPs.executeQuery();

			while (rs.next()) {
				Goods good = new Goods();
				good.setName(rs.getString(1));
				good.setCount(rs.getInt(2));
				good.setPrice(rs.getDouble(3));

				orderDetailList.add(good);
			}

		} catch (SQLException e) {
			logger.error("SQLPrintOrderDetailDAO( getOrderDetailList() )- SQLException");
			throw new DAOException("SQLPrintOrderDetailDAO( getOrderDetailList() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLPrintOrderDetailDAO( getOrderDetailList() ) - ConnectionPoolException");
			throw new DAOException("SQLPrintOrderDetailDAO( getOrderDetailList() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, firstPs, rs);

				if (secondPs != null) {
					try {
						secondPs.close();
					} catch (SQLException e) {
						logger.error("SQLPrintOrderDetailDAO(getOrderDetailList()) - error close secondPs connection");
						throw new DAOException(
								"SQLPrintOrderDetailDAO( getOrderDetailList() ) - error close secondPs connection", e);
					}
				}

				if (thirdPs != null) {
					try {
						thirdPs.close();
					} catch (SQLException e) {
						logger.error("SQLPrintOrderDetailDAO(getOrderDetailList())-error close thirdPs connection");
						throw new DAOException(
								"SQLPrintOrderDetailDAO(getOrderDetailList())-error close thirdPs connection", e);
					}
				}

			} catch (ConnectionPoolException e) {
				logger.error("SQLPrintOrderDetailDAO( getOrderDetailList() ) - error close connection");
				throw new DAOException("SQLPrintOrderDetailDAO( getOrderDetailList() ) - error close connection", e);
			}
		}

		return orderDetailList;
	}

	@Override
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
			logger.error("SQLUserFeaturesDAO( placeOrder() )- SQLException");
			throw new DAOException("SQLUserFeaturesDAO( placeOrder() ) - SQLException", e);

		} catch (ConnectionPoolException e) {
			logger.error("SQLUserFeaturesDAO( placeOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLUserFeaturesDAO( placeOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, firstPs, rs);
				if (secondPs != null) {
					try {
						secondPs.close();
					} catch (SQLException e) {
						logger.error("SQLUserFeaturesDAO( placeOrder() ) - error close secondPs connection");
						throw new DAOException("SQLUserFeaturesDAO( placeOrder() ) - error close secondPs connection",
								e);
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
			logger.error("SQLUserFeaturesDAO( enterDishFromOrder() ) - SQLException");
			throw new DAOException("SQLUserFeaturesDAO( enterDishFromOrder() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLUserFeaturesDAO( enterDishFromOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLUserFeaturesDAO( enterDishFromOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLUserFeaturesDAO( enterDishFromOrder() ) - error close connection");
				throw new DAOException("SQLUserFeaturesDAO( enterDishFromOrder() ) - error close connection", e);
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
			logger.error("SQLUserFeaturesDAO( enterDrinkFromOrder() ) - SQLException");
			throw new DAOException("SQLUserFeaturesDAO( enterDrinkFromOrder() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLUserFeaturesDAO( enterDrinkFromOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLUserFeaturesDAO( enterDrinkFromOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLUserFeaturesDAO( enterDrinkFromOrder() ) - error close connection");
				throw new DAOException("SQLUserFeaturesDAO( enterDrinkFromOrder() ) - error close connection", e);
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
			logger.error("SQLUserFeaturesDAO( enterDesertFromOrder() ) - SQLException");
			throw new DAOException("SQLUserFeaturesDAO( enterDesertFromOrder() ) - SQLException", e);
		} catch (ConnectionPoolException e) {
			logger.error("SQLUserFeaturesDAO( enterDesertFromOrder() ) - ConnectionPoolException");
			throw new DAOException("SQLUserFeaturesDAO( enterDesertFromOrder() ) - ConnectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, ps);
			} catch (ConnectionPoolException e) {
				logger.error("SQLUserFeaturesDAO( enterDesertFromOrder() ) - error close connection");
				throw new DAOException("SQLUserFeaturesDAO( enterDesertFromOrder() ) - error close connection", e);
			}
		}
	}

}
