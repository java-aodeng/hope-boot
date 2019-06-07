package com.hope.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program:hope-boot
 * @ClassName:SwaggerConfig
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-02-13 14:55
 * @Description: 丝袜哥 世界上最流行的 API 表达工具 使用 Swagger 2 构建 RESTful APIs
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //包路径
                .apis(RequestHandlerSelectors.basePackage("com.hope.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("hope-boot权限管理系统API操作文档")
                .description("后台管理中心 API 2.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("https://aodeng.cc")
                .version("2.0")
                .contact(new Contact("低调小熊猫", "https://aodeng.cc", "java@aodeng.cc"))
                .build();
    }
}