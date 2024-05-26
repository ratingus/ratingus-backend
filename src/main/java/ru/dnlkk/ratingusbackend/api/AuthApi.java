package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dnlkk.ratingusbackend.api.model.*;
import ru.dnlkk.ratingusbackend.api.dtos.JWTRequest;

import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Контроллер авторизации", description = "Авторизация, регистрация")
@RequestMapping("/auth")
public interface AuthApi {
    @Operation(
            summary = "Удаление токена",
            description = "Удаляет JWT-токен из системы и ничего не возвращает"
    )
    @GetMapping("/logout")
    ResponseEntity<String> logout();


    @Operation(
            summary = "Вход",
            description = "Возвращает JWT-токен для переданных логина и пароля"
    )
    @PostMapping("/login")
    ResponseEntity<JWTResponseDto> login(HttpServletResponse response, @RequestBody JWTRequest jwtRequest);


    @Operation(
            summary = "Регистрация",
            description = "Создаёт пользователя и возвращает JWT-токен"
    )
    @PostMapping ("/register")
    ResponseEntity<JWTResponseDto> register(HttpServletResponse response, @RequestBody JWTRegistrationDto jwtRegistrationDto);
}
