package by.epamtc.restaurant.localization;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletForChangeLanguage extends HttpServlet {

	private static final String PREVIOUS_REQUEST = "previousRequest";
	private static final String LOCALE = "locale";
	private static final long serialVersionUID = 1L;

	public ServletForChangeLanguage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String previousRequest = request.getParameter(PREVIOUS_REQUEST);

		request.getSession(true).setAttribute(LOCALE, request.getParameter(LOCALE));
		response.sendRedirect(previousRequest);
	}

}
