package com.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program:hope-boot
 * @ClassName:tetst
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-17 09:44
 * @Description: TODO
 * @Version 1.0
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HopeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopeGeneratorApplication.class, args);
    }

}
