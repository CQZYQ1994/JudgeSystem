package com.judgesystem.exception;

/**
 * 自定义异常
 *
 * @author dgb
 */
public class ManageBisException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ManageBisException(String msg) {

        super(msg);
    }

    public ManageBisException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ManageBisException of(String msg) {

        return new ManageBisException(msg);
    }

    public static ManageBisException of(String message, Throwable cause) {

        return new ManageBisException(message, cause);
    }
}
