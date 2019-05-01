package com.hope.exception;

import org.springframework.http.HttpStatus;

/**
 * @program:hope-boot
 * @ClassName:NotFoundException
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-05 21:10
 * @Description: Exception of entity not found.
 * @Version 1.0
 **/
public class NotFoundException extends HopeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
