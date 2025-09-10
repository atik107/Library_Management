package com.example.demo.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Management System (LMS) API")
                        .version("1.0")
                        .description("API documentation for Library Management System")
                        .contact(new Contact()
                                .name("Atiqul Islam")
                                .email("atiquli209@gmail.com")
                                .url("https://github.com/atik107"))
                );
    }
}
