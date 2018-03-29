package com.epam.makedon.pascalwebservice.servlet;

public class ServletRuntimeException extends RuntimeException {
    public ServletRuntimeException(String message) {
        super(message);
    }

    public ServletRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServletRuntimeException(Throwable cause) {
        super(cause);
    }
}
