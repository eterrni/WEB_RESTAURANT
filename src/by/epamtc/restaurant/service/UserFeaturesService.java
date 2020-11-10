package by.epamtc.restaurant.service;

import java.util.List;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.service.exception.ServiceException;

public interface UserFeaturesService {

	void placeOrder(Integer userId, List<Goods> orderList) throws ServiceException;

	void payOrder(Integer paymentId) throws ServiceException;

	List<Goods> getOrderDetailList(Integer orderId) throws ServiceException;
}
