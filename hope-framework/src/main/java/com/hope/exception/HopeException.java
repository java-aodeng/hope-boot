package com.hope.exception;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-18 15:39
 **/
public class HopeException extends RuntimeException {

    public HopeException() {
        super();
    }

    public HopeException(String message) {
        super(message);
    }

    public HopeException(String message, Throwable cause) {
        super(message, cause);
    }
}
