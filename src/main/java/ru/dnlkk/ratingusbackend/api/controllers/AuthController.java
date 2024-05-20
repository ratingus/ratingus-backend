package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AuthApi;
import ru.dnlkk.ratingusbackend.api.model.JWTRegistrationDto;
import ru.dnlkk.ratingusbackend.service.AuthService;
import ru.dnlkk.ratingusbackend.api.dtos.JWTRequest;

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
    public ResponseEntity login(JWTRequest jwtRequest) {
        try {
            return ResponseEntity.ok(authService.signIn(jwtRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity register(JWTRegistrationDto jwtRegistrationDto) {
        try {
            return ResponseEntity.ok(authService.signUp(jwtRegistrationDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
