package com.hope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HopeSsoServerApplication {

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(HopeSsoServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HopeSsoServerApplication.class, args);
		LOGGER.info("Hope-Sso-Server启动成功！温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行");
	}

}
