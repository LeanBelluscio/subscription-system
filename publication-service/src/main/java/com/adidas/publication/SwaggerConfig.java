package com.adidas.publication;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lbelluscio
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.adidas.publication.controller"))              
          .paths(PathSelectors.any())                        
          .build()
          .apiInfo(apiInfo());
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfo("Adidas Publication API", 
        		"REST API for Adidas Publication Service", 
        		"1.0",
        		"", 
        		new Contact("Leandro Belluscio", "", "leanbelluscio@gmail.com"),
        		"", 
        		"", Collections.emptyList());
    }
}