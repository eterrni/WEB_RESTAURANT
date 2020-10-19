package by.epamtc.restaurant.controller.command.impl.add_to_order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.bean.goods.desert.Desert;
import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.controller.command.Command;

public class AddDesertToOrderCommand implements Command {

	private static final String ATTRIBUTE_ORDER = "order";
	private static final String ATTRIBUTE_DESERT_LIST = "desertList";
	
	private static final String PARAMETER_ID = "id";
	private static final String MENU_PAGE = "Controller?command=go_to_menu_page";

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute(ATTRIBUTE_ORDER) == null) {
			Order order = new Order();
			session.setAttribute(ATTRIBUTE_ORDER, order);
		}

		Integer id = Integer.parseInt(request.getParameter(PARAMETER_ID)) - 1;
		List<Desert> desertList = (List<Desert>) session.getAttribute(ATTRIBUTE_DESERT_LIST);
		Desert desert = desertList.get(id);

		Order order = (Order) session.getAttribute(ATTRIBUTE_ORDER);
		order.getOrderList().add(desert);

		response.sendRedirect(MENU_PAGE);
	}

}
