package by.epamtc.restaurant.controller.command.impl.utility;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.service.DownloadMenuService;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadMenuDataUtility {

	private static final String ATTRIBUTE_ERROR = "error";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final DownloadMenuService downloadMenuService = factory.getDownloadMenuService();

	private static final DownloadMenuDataUtility instance = new DownloadMenuDataUtility();

	public static DownloadMenuDataUtility getInstance() {
		return instance;
	}

	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<Dish> dishList = downloadMenuService.downloadDish();
			List<Drink> drinkList = downloadMenuService.downloadDrink();
			List<Desert> desertList = downloadMenuService.downloadDesert();

			request.getSession().setAttribute("dishList", dishList);
			request.getSession().setAttribute("drinkList", drinkList);
			request.getSession().setAttribute("desertList", desertList);
		} catch (ServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
