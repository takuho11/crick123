package cn.topcheer.halberd.app.api.framework.exception;

public class UserErrorException extends RuntimeException{
    public UserErrorException() {
    }

    public UserErrorException(String message) {
        super(message);
    }

    public UserErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserErrorException(Throwable cause) {
        super(cause);
    }

    public UserErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
