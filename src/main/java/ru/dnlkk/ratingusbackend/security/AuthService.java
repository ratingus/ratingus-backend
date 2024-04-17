package ru.dnlkk.ratingusbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.model.JWTResponseDto;
import ru.dnlkk.ratingusbackend.api.model.UserLoginDto;
import ru.dnlkk.ratingusbackend.api.model.UserRegistrationDto;
import ru.dnlkk.ratingusbackend.service.UserService;
import ru.dnlkk.ratingusbackend.utils.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

//    public JWTResponseDto signUp(UserRegistrationDto userRegistrationDto) {
//
//        var user = User.builder()
//                .username(userRegistrationDto.getNa())
//                .email(userRegistrationDto.getEmail())
//                .password(passwordEncoder.encode(userRegistrationDto.getPassword()))
//                .role(Role.ROLE_USER)
//                .build();
//
//        userService.create(user);
//
//        var jwt = jwtService.generateToken(user);
//        return new JWTResponseDto(jwt);
//    }
//
//    /**
//     * Аутентификация пользователя
//     *
//     * @param request данные пользователя
//     * @return токен
//     */
//    public JWTResponseDto signIn(UserLoginDto userLoginDto) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                userLoginDto.getUsername(),
//                userLoginDto.getPassword()
//        ));
//
//        var user = userService
//                .userDetailsService()
//                .loadUserByUsername(request.getUsername());
//
//        var jwt = jwtService.generateToken(user);
//        return new JWTResponseDto(jwt);
//    }
}
