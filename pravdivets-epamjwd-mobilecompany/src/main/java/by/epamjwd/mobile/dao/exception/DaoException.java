package by.epamjwd.mobile.dao.exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = 6036396863670585075L;

	public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
    
    public DaoException(String message, Throwable cause) {
    	super(message, cause);
    }
    
}
