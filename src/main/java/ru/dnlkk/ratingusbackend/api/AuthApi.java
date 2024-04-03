package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dnlkk.ratingusbackend.api.model.JWTResponseDto;
import ru.dnlkk.ratingusbackend.api.model.UserDto;

@Tag(name = "Контроллер авторизации", description = "Авторизация, регистрация")
@RequestMapping("/auth")
public interface AuthApi {
    @Operation(
            summary = "todo",
            description = "todo"
    )
    @GetMapping("/logout")
    ResponseEntity<Void> logout();

    @Operation(
            summary = "todo",
            description = "todo"
    )
    @GetMapping("/login") //todo: нужно ли передавать что-то
    ResponseEntity<JWTResponseDto> login();

    @Operation(
            summary = "todo",
            description = "todo"
    )
    @GetMapping("/register")
    ResponseEntity<JWTResponseDto> register(@RequestBody UserDto userDto);
}
