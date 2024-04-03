package ru.dnlkk.ratingusbackend.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Ratingus Api",
                description = "Ratingus", version = "0.0.1",
                contact = @Contact(
                        name = "Сапегин Павел",
                        email = "pavelsapegin18@gmail.com"
                )
        )
)
public class SwaggerConfig {
    //просмотреть документацию: http://localhost:8080/swagger-ui/index.html#/
}
