package ru.dnlkk.ratingusbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.model.*;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.service.UserService;
import ru.dnlkk.ratingusbackend.utils.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JWTResponseDto signUp(JWTRegistrationDto jwtRegistrationDto) {
        var user = User.builder()
                .username(jwtRegistrationDto.getLogin())
                .password(passwordEncoder.encode(jwtRegistrationDto.getPassword()))
                .build();

        userService.create((ru.dnlkk.ratingusbackend.model.User) user);
        var jwt = jwtService.generateToken(user);
        return new JWTResponseDto(jwt);
    }
    public JWTResponseDto signIn(JWTRequest jwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getLogin(),
                jwtRequest.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(jwtRequest.getLogin());

        var jwt = jwtService.generateToken(user);
        return new JWTResponseDto(jwt);
    }
}
