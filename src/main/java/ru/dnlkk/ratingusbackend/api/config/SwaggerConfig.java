package ru.dnlkk.ratingusbackend.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
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
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "сервер для разработки"),
                @Server(url = "https://ratingus.fun/spring-api", description = "продуктовый сервер")
        }
)
public class SwaggerConfig {
}
