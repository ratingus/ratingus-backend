package ru.dnlkk.ratingusbackend.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
}
