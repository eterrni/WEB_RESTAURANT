package by.epamtc.restaurant.dao;

import java.util.List;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.dao.exception.DAOException;

public interface DownloadMenuDAO {

	List<Dish> downloadDish() throws DAOException;

	List<Drink> downloadDrink() throws DAOException;

	List<Desert> downloadDesert() throws DAOException;
}
