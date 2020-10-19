package by.epamtc.restaurant.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.impl.SQLPlaceOrderDAO;
import by.epamtc.restaurant.service.PlaceOrderService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class PlaceOrderImpl implements PlaceOrderService {

	public static final SQLPlaceOrderDAO sqlPlaceOrderDAO = new SQLPlaceOrderDAO();

	public void placeOrder(Integer userId, List<Goods> orderList) throws ServiceException {

		Map<String, Integer> dishMap = new HashMap<>();
		Map<String, Integer> drinkMap = new HashMap<>();
		Map<String, Integer> desertMap = new HashMap<>();

		divisionOrder(orderList, dishMap, drinkMap, desertMap);

		try {
			Integer orderId = sqlPlaceOrderDAO.placeOrder(userId);
			if (dishMap.size() != 0) {
				sqlPlaceOrderDAO.enterDishFromOrder(orderList, orderId, dishMap);
			}
			if (drinkMap.size() != 0) {
				sqlPlaceOrderDAO.enterDrinkFromOrder(orderList, orderId, drinkMap);
			}
			if (desertMap.size() != 0) {
				sqlPlaceOrderDAO.enterDesertFromOrder(orderList, orderId, desertMap);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	private void divisionOrder(List<Goods> orderList, Map<String, Integer> dishMap, Map<String, Integer> drinkMap,
			Map<String, Integer> desertMap) {
		for (Goods good : orderList) {

			if (good instanceof Dish) {
				int flag = 0;
				for (Map.Entry<String, Integer> entry : dishMap.entrySet()) {
					if (((Dish) good).getName().equals(entry.getKey())) {
						dishMap.put(((Dish) good).getName(), entry.getValue() + 1);
						flag = 1;
					}
				}
				if (flag == 0) {
					dishMap.put(((Dish) good).getName(), 1);
				}
			}

			if (good instanceof Drink) {
				int flag = 0;
				for (Map.Entry<String, Integer> entry : drinkMap.entrySet()) {
					if (((Drink) good).getName().equals(entry.getKey())) {
						drinkMap.put(((Drink) good).getName(), entry.getValue() + 1);
						flag = 1;
					}
				}
				if (flag == 0) {
					drinkMap.put(((Drink) good).getName(), 1);
				}
			}

			if (good instanceof Desert) {
				int flag = 0;
				for (Map.Entry<String, Integer> entry : desertMap.entrySet()) {
					if (((Desert) good).getName().equals(entry.getKey())) {
						desertMap.put(((Desert) good).getName(), entry.getValue() + 1);
						flag = 1;
					}
				}
				if (flag == 0) {
					desertMap.put(((Desert) good).getName(), 1);
				}
			}

		}
	}
}
