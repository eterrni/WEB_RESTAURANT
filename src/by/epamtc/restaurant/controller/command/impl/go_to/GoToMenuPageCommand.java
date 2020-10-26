package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.controller.command.impl.utility.DownloadMenuDataUtility;

public class GoToMenuPageCommand implements Command {

	private static final String MENU_PAGE = "WEB-INF/jsp/menu_page.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		if (request.getSession().getAttribute("drinkList") == null
				|| request.getSession().getAttribute("dishList") == null
				|| request.getSession().getAttribute("desertList") == null) {
			
			DownloadMenuDataUtility downloadMenuDataUtility = DownloadMenuDataUtility.getInstance();
			downloadMenuDataUtility.download(request, response);
			
		}
		request.getRequestDispatcher(MENU_PAGE).forward(request, response);

	}

}
