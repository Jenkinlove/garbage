package com.xiong.common.utils;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 657378777056762471L;

    public ServiceException() {
        super();
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
