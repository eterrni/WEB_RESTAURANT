package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.controller.command.impl.utility.DownloadMenuDataUtility;

public class GoToMenuPageCommand implements Command {

	private static final String MENU_PAGE = "WEB-INF/jsp/menu_page.jsp";
	
	private static final String ATTRIBUTE_DRINK_LIST = "drinkList";
	private static final String ATTRIBUTE_DISH_LIST = "dishList";
	private static final String ATTRIBUTE_DESERT_LIST = "desertList";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		if (request.getSession().getAttribute(ATTRIBUTE_DRINK_LIST) == null
				|| request.getSession().getAttribute(ATTRIBUTE_DISH_LIST) == null
				|| request.getSession().getAttribute(ATTRIBUTE_DESERT_LIST) == null) {
			
			DownloadMenuDataUtility downloadMenuDataUtility = DownloadMenuDataUtility.getInstance();
			downloadMenuDataUtility.download(request, response);
			
		}
		request.getRequestDispatcher(MENU_PAGE).forward(request, response);

	}

}
