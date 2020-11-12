package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.dao.DownloadMenuDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;

public class SQLDownloadMenuDAO implements DownloadMenuDAO {

	private static final String SELECT_DISH = "SELECT * FROM rest_db.dishes";
	private static final String SELECT_DRINK = "SELECT * FROM rest_db.drinks;";
	private static final String SELECT_DESERT = "SELECT * FROM rest_db.deserts;";

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final Logger logger = LogManager.getLogger(SQLDownloadMenuDAO.class);

	/**
	 * Uploading all dishes to the list
	 * 
	 * @return List of all dishes
	 * @exception DAOException if occurred severe problem with database
	 */
	@Override
	public List<Dish> downloadDish() throws DAOException {

		List<Dish> dishList = new ArrayList<>();
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			cn = connectionPool.takeConnection();
			st = cn.createStatement();
			rs = st.executeQuery(SELECT_DISH);

			while (rs.next()) {
				Integer id = Integer.parseInt(rs.getString(1));
				String name = rs.getString(2);
				Double price = Double.parseDouble(rs.getString(3));
				String description = rs.getString(4);

				Dish dish = new Dish();
				dish.setId(id);
				dish.setName(name);
				dish.setPrice(price);
				dish.setDescription(description);

				dishList.add(dish);
			}

		} catch (SQLException e) {
			logger.error("Download dishList sqlException");
			throw new DAOException("Download dishList sqlException", e);
		} catch (ConnectionPoolException e) {
			logger.error("Download dishList connectionPoolException");
			throw new DAOException("Download dishList connectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, st, rs);
			} catch (ConnectionPoolException e) {
				logger.error("Download dishList error close connection");
				throw new DAOException("Download dishList error close connection", e);
			}
		}

		return dishList;
	}

	/**
	 * Uploading all drinks to the list
	 * 
	 * @return List of all drinks
	 * @exception DAOException if occurred severe problem with database
	 */
	@Override
	public List<Drink> downloadDrink() throws DAOException {

		List<Drink> drinkList = new ArrayList<>();
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			cn = connectionPool.takeConnection();
			st = cn.createStatement();
			rs = st.executeQuery(SELECT_DRINK);

			while (rs.next()) {
				Integer id = Integer.parseInt(rs.getString(1));
				String name = rs.getString(2);
				Double price = Double.parseDouble(rs.getString(3));
				String description = rs.getString(4);

				Drink drink = new Drink();
				drink.setId(id);
				drink.setName(name);
				drink.setPrice(price);
				drink.setDescription(description);

				drinkList.add(drink);
			}

		} catch (SQLException e) {
			logger.error("Download drinkList sqlException");
			throw new DAOException("Download drinkList sqlException", e);
		} catch (ConnectionPoolException e) {
			logger.error("Download drinkList connectionPoolException");
			throw new DAOException("Download drinkList connectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, st, rs);
			} catch (ConnectionPoolException e) {
				logger.error("Download drinkList error close connection");
				throw new DAOException("Download drinkList error close connection", e);
			}
		}

		return drinkList;
	}

	/**
	 * Uploading all deserts to the list
	 * 
	 * @return List of all deserts
	 * @exception DAOException if occurred severe problem with database
	 */
	@Override
	public List<Desert> downloadDesert() throws DAOException {

		List<Desert> desertList = new ArrayList<>();
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			cn = connectionPool.takeConnection();
			st = cn.createStatement();
			rs = st.executeQuery(SELECT_DESERT);

			while (rs.next()) {
				Integer id = Integer.parseInt(rs.getString(1));
				String name = rs.getString(2);
				Double price = Double.parseDouble(rs.getString(3));
				String description = rs.getString(4);

				Desert desert = new Desert();
				desert.setId(id);
				desert.setName(name);
				desert.setPrice(price);
				desert.setDescription(description);

				desertList.add(desert);
			}

		} catch (SQLException e) {
			logger.error("Download desertList sqlException");
			throw new DAOException("Download desertList sqlException", e);
		} catch (ConnectionPoolException e) {
			logger.error("Download desertList connectionPoolException");
			throw new DAOException("Download desertList connectionPoolException", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, st, rs);
			} catch (ConnectionPoolException e) {
				logger.error("Download desertList error close connection");
				throw new DAOException("Download desertList error close connection", e);
			}
		}

		return desertList;
	}

}
