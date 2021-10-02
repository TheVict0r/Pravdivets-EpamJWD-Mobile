package by.epamjwd.mobile.service.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 8231950294206877751L;

	public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
    
    public ServiceException(String message, Throwable cause) {
    	super(message, cause);
    }
}
