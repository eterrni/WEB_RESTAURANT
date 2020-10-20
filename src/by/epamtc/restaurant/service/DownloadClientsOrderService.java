package by.epamtc.restaurant.service;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.service.exception.ServiceException;

public interface DownloadClientsOrderService {

	List<Order> downloadUnconfirmedClientsOrderList() throws ServiceException;
	List<Order> downloadConfirmedClientsOrderList() throws ServiceException;
}
