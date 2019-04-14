package com.hope.core.exception;

/**
 * @author xuxueli 2018-05-02 21:10:28
 */
public class CodeGenerateException extends RuntimeException {
    private static final long serialVersionUID = 42L;

    public CodeGenerateException(String msg) {
        super(msg);
    }

    public CodeGenerateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CodeGenerateException(Throwable cause) {
        super(cause);
    }

}
