package com.amalitech.techBuddy.config;

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

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiEndpointInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.amalitech.techBuddy.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiEndpointInfo(){
        return new ApiInfoBuilder()
                .title("TechBuddy API")
                .description("TechBuddy is a simple API that helps Amalitech partners to upload and retrieve images and videos to Cloudinary.")
                .contact(new Contact("Kabera Clapton", "github.com/KaberaClapton", "ckabera6@gmail.com"))
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
