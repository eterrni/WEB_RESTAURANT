package by.epamtc.restaurant.dao;

import by.epamtc.restaurant.dao.impl.SQLAdminDAO;
import by.epamtc.restaurant.dao.impl.SQLDownloadAdminInfoDAO;
import by.epamtc.restaurant.dao.impl.SQLDownloadMenuDAO;
import by.epamtc.restaurant.dao.impl.SQLDownloadUserOrderDAO;
import by.epamtc.restaurant.dao.impl.SQLUserDAO;
import by.epamtc.restaurant.dao.impl.SQLUserFeaturesDAO;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private static final UserDAO sqlUserDAO = new SQLUserDAO();
	private static final AdminDAO sqlAdminDAO = new SQLAdminDAO();
	private static final UserFeaturesDAO sqlUserFeaturesDAO = new SQLUserFeaturesDAO();

	private static final DownloadMenuDAO downloadMenuDAO = new SQLDownloadMenuDAO();
	private static final DownloadUserOrderDAO downloadUserOrderDAO = new SQLDownloadUserOrderDAO();
	private static final DownloadAdminInfoDAO downloadAdminInfoDAO = new SQLDownloadAdminInfoDAO();

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return sqlUserDAO;
	}

	public AdminDAO getAdminDAO() {
		return sqlAdminDAO;
	}

	public UserFeaturesDAO getUserFeaturesDAO() {
		return sqlUserFeaturesDAO;
	}

	public DownloadMenuDAO getDownloadMenuDAO() {
		return downloadMenuDAO;
	}

	public DownloadAdminInfoDAO getDownloadAdminInfoDAO() {
		return downloadAdminInfoDAO;
	}

	public DownloadUserOrderDAO getDownloadUserOrderDAO() {
		return downloadUserOrderDAO;
	}
}
