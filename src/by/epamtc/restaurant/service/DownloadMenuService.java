package by.epamtc.restaurant.service;

import java.util.List;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.service.exception.ServiceException;

public interface DownloadMenuService {

	List<Drink> downloadDrink() throws ServiceException;

	List<Dish> downloadDish() throws ServiceException;

	List<Desert> downloadDesert() throws ServiceException;

}
