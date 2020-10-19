package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;

public class GoToUpdateUserDataPageCommand implements Command {

	private static final String UPDATE_USER_DATA_PAGE = "WEB-INF/jsp/update_user_data_page.jsp";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher(UPDATE_USER_DATA_PAGE).forward(request, response);

	}

}
