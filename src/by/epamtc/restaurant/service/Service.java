package by.epamtc.restaurant.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.service.exception.ServiceException;

public interface Service {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
