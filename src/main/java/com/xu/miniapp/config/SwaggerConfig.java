package com.xu.miniapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author CharleyXu Created on 2019/3/19.
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {

    @Bean
    public Docket apiSwagger2Service() {
        ApiInfo apiInfo = new ApiInfoBuilder()//
            .title("REST APIs")//
            .description("MiniApp Interfaces")//
            .version("1.0")//
            .build();
        return new Docket(DocumentationType.SWAGGER_2)//
            .groupName("rest-api")//
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/**"))
            .build();
    }
}
