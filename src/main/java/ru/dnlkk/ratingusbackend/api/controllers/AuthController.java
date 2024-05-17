package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AuthApi;
import ru.dnlkk.ratingusbackend.api.model.JWTRegistrationDto;
import ru.dnlkk.ratingusbackend.api.model.JWTResponseDto;
import ru.dnlkk.ratingusbackend.api.model.UserLoginDto;
import ru.dnlkk.ratingusbackend.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;
    @Override
    public ResponseEntity<String> logout() {
        System.out.println("qwerqwer");
        return ResponseEntity.ok("wqerqwer");
    }

    @Override
    public ResponseEntity<JWTResponseDto> login(UserLoginDto userLoginDto) {
        return null;
    }

    @Override
    public ResponseEntity<JWTResponseDto> register(JWTRegistrationDto jwtRegistrationDto) {
        System.out.println("3werqwerqwer");
        return ResponseEntity.ok(authService.signUp(jwtRegistrationDto));
    }
}
