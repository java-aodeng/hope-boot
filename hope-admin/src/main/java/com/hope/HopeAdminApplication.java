package com.hope;

import com.cxytiandi.encrypt.springboot.annotation.EnableEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEncrypt//注解开启加解密操作
@SpringBootApplication
public class HopeAdminApplication {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(HopeAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HopeAdminApplication.class, args);
        LOGGER.info("hope-admin服务启动成功！温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行");
    }
}
