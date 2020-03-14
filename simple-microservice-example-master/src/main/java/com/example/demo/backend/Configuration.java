package com.example.demo.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Configuration.
 */
@org.springframework.context.annotation.Configuration
@EnableSwagger2
@Import({Dao.class})
public class Configuration {

    @Value("${prop}")
    private String property;

    /**
     * Dao class bean.
     *
     * @return the dao
     */
    @Bean
    public Dao dao() {
        System.out.println("This is property -- " + property + " -- from config file");
        return new Dao();
    }

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Example Microserivce",
                "Microservice example architecture",
                null,
                null,
                null,
                null,
                null,
                Stream.of(new StringVendorExtension("Company", "Name")).collect(Collectors.toList())
        );
    }


}
