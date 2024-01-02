package com.softwarefoundation.parkingapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(info());

    }


    private Info info() {
        return new Info().title("REST API - Parking")
                .description("REST API - Parking")
                .version("v1.0.0")
                .license(license())
                .contact(contact());
    }

    private Contact contact() {
        return new Contact().name("Dherkyan R. Silva").email("dherkyan.r.silva@gmail.com");
    }

    private License license() {
        return new License().name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");
    }

}
