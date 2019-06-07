package com.hope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HopeSsoServerApplication {

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(HopeSsoServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HopeSsoServerApplication.class, args);
		LOGGER.info("hope-sso-server服务启动成功！温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行");
	}

}
