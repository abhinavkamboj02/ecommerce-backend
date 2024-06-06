package com.device.bazzar.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Backend APIs for Device Bazzar")
                .description("Developed by Abhinav Kamboj💻")
                .contact(new Contact().email("abhik1077@gmail.com").name("Abhinav Kamboj"))
                .version("1.0"))
                .externalDocs(new ExternalDocumentation()
        );

    }



}
