package by.epamtc.restaurant.controller.command.impl.add_remove_goods_order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.controller.command.Command;

public class RemoveGoodsFromOrderCommand implements Command {

	private static final String ATTRIBUTE_USER = "user";
	private static final String ATTRIBUTE_ORDER = "order";

	private static final String PARAMETER_REMOVE_GOODS_NAME = "remove_goods_name";
	private static final String ORDER_PAGE = "Controller?command=go_to_order_page";
	private static final String WELCOME_PAGE = "Controller?command=go_to_welcome_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute(ATTRIBUTE_USER) != null || request.getParameter(PARAMETER_REMOVE_GOODS_NAME) != null) {

			Order order = (Order) session.getAttribute(ATTRIBUTE_ORDER);
			List<Goods> orderList = order.getOrderList();
			for (Goods good : orderList) {
				if (good.getName().equals(request.getParameter(PARAMETER_REMOVE_GOODS_NAME))) {
					orderList.remove(good);
					break;
				}
			}
			if (orderList.size() == 0) {
				session.removeAttribute(ATTRIBUTE_ORDER);
			}
			response.sendRedirect(ORDER_PAGE);
		} else {
			response.sendRedirect(WELCOME_PAGE);
		}
	}
}
