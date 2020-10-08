package by.epamtc.restaurant.service.impl;

import java.util.List;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.dao.DAOFactory;
import by.epamtc.restaurant.dao.DownloadMenuDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.service.DownloadMenuService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadMenuImpl implements DownloadMenuService {

	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final DownloadMenuDAO downloadMenuDAO = factory.getDownloadMenuDAO();

	@Override
	public List<Drink> downloadDrink() throws ServiceException {
		List<Drink> drinkList = null;
		try {
			drinkList = downloadMenuDAO.downloadDrink();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drinkList;
	}

	@Override
	public List<Dish> downloadDish() throws ServiceException {
		List<Dish> dishList = null;
		try {
			dishList = downloadMenuDAO.downloadDish();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return dishList;
	}

	@Override
	public List<Desert> downloadDesert() throws ServiceException {
		List<Desert> desertList = null;
		try {
			desertList = downloadMenuDAO.downloadDesert();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return desertList;
	}

}
