package ru.dnlkk.ratingusbackend.api.controllers;

import org.springframework.http.ResponseEntity;
import ru.dnlkk.ratingusbackend.api.AuthApi;
import ru.dnlkk.ratingusbackend.api.dtos.JWTRegistrationDto;
import ru.dnlkk.ratingusbackend.api.dtos.JWTResponseDto;

public class AuthController implements AuthApi {
    @Override
    public ResponseEntity<Void> logout() {
        return null;
    }

    @Override
    public ResponseEntity<JWTResponseDto> login(JWTResponseDto jwtResponseDto) {
        return null;
    }

    @Override
    public ResponseEntity<JWTResponseDto> register(JWTRegistrationDto jwtRegistrationDto) {
        return null;
    }
}
