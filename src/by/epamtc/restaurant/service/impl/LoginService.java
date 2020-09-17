package by.epamtc.restaurant.service.impl;

import by.epamtc.restaurant.dao.UserDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.service.Service;
import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.dao.DAOFactory;

public class LoginService implements Service{

	private static final DAOFactory instance = DAOFactory.getInstance();
	private final UserDAO userDAO = instance.getUserDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		try {
			if(userDAO.userPresenceInSystem(login, password)) {
				userDAO.signIn(login, password);
				response.setContentType("text/html");
				response.getWriter().write("<h1>Succsesfull logination, hello!!</h1>");
				
			} else {
				request.getRequestDispatcher("WEB-INF/jsp/registration_page.jsp").forward(request, response);
			}
		} catch (DAOException | IOException | ServletException e) {
			throw new ServiceException(e);
		}
	}
	
	
}
