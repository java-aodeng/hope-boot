package com.hope.exception;

/**
 * @program:hope-boot
 * @ClassName:SsoExcepption
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-31 12:15
 * @Description: SsoExcepption
 * @Version 1.0
 **/
public class SsoExcepption extends RuntimeException{

    private static final long serialVersionUID = 42L;

    public SsoExcepption(String msg) {
        super(msg);
    }

    public SsoExcepption(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SsoExcepption(Throwable cause) {
        super(cause);
    }
}
