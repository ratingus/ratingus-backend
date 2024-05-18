package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AuthApi;
import ru.dnlkk.ratingusbackend.api.model.JWTRegistrationDto;
import ru.dnlkk.ratingusbackend.api.model.JWTRequest;
import ru.dnlkk.ratingusbackend.api.model.JWTResponseDto;
import ru.dnlkk.ratingusbackend.api.model.UserLoginDto;
import ru.dnlkk.ratingusbackend.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;
    @Override
    public ResponseEntity<String> logout() {
        System.out.println("logout");
        return ResponseEntity.ok("Пользователь вышел из системы");
    }

    @Override
    public ResponseEntity<JWTResponseDto> login(JWTRequest jwtRequest) {
        System.out.println("login");
        return ResponseEntity.ok(authService.signIn(jwtRequest));
    }

    @Override
    public ResponseEntity<JWTResponseDto> register(JWTRegistrationDto jwtRegistrationDto) {
        System.out.println("register");
        return ResponseEntity.ok(authService.signUp(jwtRegistrationDto));
    }
}
