package by.epamtc.restaurant.dao;

import java.util.List;
import java.util.Map;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.dao.exception.DAOException;

public interface UserFeaturesDAO {

	Integer placeOrder(Integer userId) throws DAOException;

	void payOrder(Integer paymentId) throws DAOException;

	List<Goods> getOrderDetailList(Integer orderId) throws DAOException;

	void enterDishFromOrder(List<Goods> orderList, Integer orderId, Map<String, Integer> dishMap) throws DAOException;

	void enterDrinkFromOrder(List<Goods> orderList, Integer orderId, Map<String, Integer> drinkMap) throws DAOException;

	void enterDesertFromOrder(List<Goods> orderList, Integer orderId, Map<String, Integer> desertMap)
			throws DAOException;

}
