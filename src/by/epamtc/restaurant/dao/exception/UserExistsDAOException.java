package by.epamtc.restaurant.dao.exception;

public class UserExistsDAOException extends Exception{

	private static final long serialVersionUID = 6556808188277433031L;

	public UserExistsDAOException() {
		super();
	}
	
	public UserExistsDAOException(String message) {
		super(message);
	}
	
	public UserExistsDAOException(Exception e) {
		super(e);
	}

	public UserExistsDAOException(String message, Exception e) {
		super(message, e);
	}
}
