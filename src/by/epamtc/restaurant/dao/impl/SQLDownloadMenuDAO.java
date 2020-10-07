package by.epamtc.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.dao.DownloadMenuDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.connection_pool.ConnectionPool;
import by.epamtc.restaurant.dao.impl.connection_pool.exception.ConnectionPoolException;

public class SQLDownloadMenuDAO implements DownloadMenuDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static final String SELECT_DISH = "SELECT * FROM rest_db.dishes";
	private static final String SELECT_DRINK = "SELECT * FROM rest_db.drinks;";
	private static final String SELECT_DESERT = "SELECT * FROM rest_db.deserts;";

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

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Download dish exception", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException("Error close connection", e);
			}
		}

		// TODO Auto-generated method stub
		return dishList;
	}

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

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Download dish exception", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException("Error close connection", e);
			}
		}

		// TODO Auto-generated method stub
		return drinkList;
	}

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

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Download dish exception", e);
		} finally {
			try {
				connectionPool.closeConnection(cn, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException("Error close connection", e);
			}
		}

		// TODO Auto-generated method stub
		return desertList;
	}

}
