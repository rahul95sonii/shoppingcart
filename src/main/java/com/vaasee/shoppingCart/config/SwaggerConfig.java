package com.vaasee.shoppingCart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 
 * author rahul.soni
 * 
 * */

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Security with Swagger")
                        .version("1.0")
                        .description("API documentation for Spring Security with Swagger"));
    }
}
