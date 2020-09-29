package by.epamtc.restaurant.service.exception;

public class UserExistsServiceException extends Exception {

	private static final long serialVersionUID = -785030148744980780L;

	public UserExistsServiceException() {
		super();
	}
	
	public UserExistsServiceException(String message) {
		super(message);
	}
	
	public UserExistsServiceException(Exception e) {
		super(e);
	}

	public UserExistsServiceException(String message, Exception e) {
		super(message, e);
	}
	
}
