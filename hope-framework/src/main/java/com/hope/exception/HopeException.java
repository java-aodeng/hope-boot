package com.hope.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * HopeException
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-18 15:39
 **/
public abstract class HopeException extends RuntimeException {

    /**
     * Error errorData.
     */
    private Object errorData;

    public HopeException() {
        super();
    }

    public HopeException(String message) {
        super(message);
    }

    public HopeException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public HopeException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
