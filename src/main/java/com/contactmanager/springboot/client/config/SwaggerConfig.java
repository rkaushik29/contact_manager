package com.contactmanager.springboot.client.config;

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
//	@Bean
//	public Docket createRestApi(){
//	  return new Docket(DocumentationType.SWAGGER_2)
//	      .apiInfo(apiInfo())
//	      .select()
//	      .apis(RequestHandlerSelectors.basePackage("com.contactmanager.springboot.client.controller"))
//	      .paths(PathSelectors.any())
//	      .build();
//	}
//	
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Contact Manager API")
//                .description("API for managing contacts")
//                .version("1.0.0")
//                .termsOfServiceUrl("http://terms-of-service.url")
//                .contact(new Contact("Rohit Kaushik", "", "rohit.rk.rk1@gmail.com"))
//                .license("MIT")
//                .licenseUrl("")
//                .build();
//    }
}