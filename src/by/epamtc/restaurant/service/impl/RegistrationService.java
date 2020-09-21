package by.epamtc.restaurant.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.bean.User;
import by.epamtc.restaurant.dao.DAOFactory;
import by.epamtc.restaurant.dao.UserDAO;
import by.epamtc.restaurant.service.Service;
import by.epamtc.restaurant.service.exception.ServiceException;

public class RegistrationService implements Service {

	private final DAOFactory instance = DAOFactory.getInstance();
	private final UserDAO userDAO = instance.getUserDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String patronymic = request.getParameter("patronymic");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String phone_number = request.getParameter("phone_number");
		Integer age = null;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (NumberFormatException e) {
			throw new ServiceException(e);
		}
		String email = request.getParameter("email");

		//////////////////////////////////////////////////////////////
		Integer role_id = 2; // 1-admin, 2-user
		//////////////////////////////////////////////////////////////
		
		User user = new User(name, surname, patronymic, login, password, phone_number, age, email, role_id);

		try {
			if (userDAO.userPresenceInSystem(user)) {
				try {
					request.getRequestDispatcher("WEB-INF/jsp/login_page.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					throw new ServiceException(e);
				}
			} else {
				userDAO.registartion(user);

			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

}
