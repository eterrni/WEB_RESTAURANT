package by.epamtc.restaurant.dao;

import by.epamtc.restaurant.dao.impl.SQLDownloadClientsOrderDAO;
import by.epamtc.restaurant.dao.impl.SQLDownloadMenuDAO;
import by.epamtc.restaurant.dao.impl.SQLDownloadUserOrderDAO;
import by.epamtc.restaurant.dao.impl.SQLUserDAO;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private static final UserDAO sqlUserDAO = new SQLUserDAO();
	private static final DownloadMenuDAO downloadMenuDAO = new SQLDownloadMenuDAO();
	private static final DownloadUserOrderDAO downloadUserOrderDAO = new SQLDownloadUserOrderDAO();
	private static final DownloadClientsOrderDAO downloadClientsOrderDAO = new SQLDownloadClientsOrderDAO();

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return sqlUserDAO;
	}

	public DownloadMenuDAO getDownloadMenuDAO() {
		return downloadMenuDAO;
	}

	public DownloadClientsOrderDAO getDownloadClientsOrderDAO() {
		return downloadClientsOrderDAO;
	}

	public DownloadUserOrderDAO getDownloadUserOrderDAO() {
		return downloadUserOrderDAO;
	}
}
