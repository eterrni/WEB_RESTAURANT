package by.epamtc.restaurant.service;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.payment.Payment;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.service.exception.ServiceException;

public interface DownloadAdminInfoService {

	List<Order> downloadUnconfirmedClientsOrderList() throws ServiceException;

	List<Order> downloadConfirmedClientsOrderList() throws ServiceException;

	List<User> downloadUserList() throws ServiceException;

	List<User> downloadEmployeeList() throws ServiceException;

	List<Payment> downloadPaymentList() throws ServiceException;
}
