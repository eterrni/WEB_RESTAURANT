package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.goods.dish.Dish;
import by.epamtc.restaurant.bean.goods.drink.Drink;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.DownloadMenuService;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadMenuDataCommand implements Command {

	private static final Logger logger = LogManager.getLogger(LoginCommand.class);

	private static final ServiceFactory factory = ServiceFactory.getInstance();

	private static final DownloadMenuService downloadMenuService = factory.getDownloadMenuService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Dish> dishList= downloadMenuService.downloadDish();
			List<Drink> drinkList= downloadMenuService.downloadDrink();
			List<Desert> desertList= downloadMenuService.downloadDesert();
			
			request.getSession().setAttribute("dishList", dishList);
			request.getSession().setAttribute("drinkList", drinkList);
			request.getSession().setAttribute("desertList", desertList);
		} catch(ServiceException e) {
			//todo log
		}
	}
}
