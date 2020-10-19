package by.epamtc.restaurant.service;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.service.exception.ServiceException;

public interface DownloadUserOrderService {

	List<Order> download(Integer userId) throws ServiceException;
}
