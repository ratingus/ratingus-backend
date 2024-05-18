package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.dnlkk.ratingusbackend.api.model.JWTRegistrationDto;
import ru.dnlkk.ratingusbackend.api.model.JWTRequest;
import ru.dnlkk.ratingusbackend.api.model.JWTResponseDto;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.security.JwtTokenService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserService userService;
    private final JwtTokenService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JWTResponseDto signUp(JWTRegistrationDto request) {
        // Проверка входных данных
        if (request == null || !isValidRegistrationData(request)) {
            log.warn("Invalid registration data: {}", request);
            throw new IllegalArgumentException("Invalid registration data");
        }

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPatronymic(request.getPatronymic());
        user.setBirthDate(request.getBirthDate());
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            User registeredUser = userService.registerUser(user);
            log.info("User registered: {}", registeredUser);

            String jwt = jwtService.generateToken(new UserDetailsImpl(registeredUser));
            log.debug("JWT token generated: {}", jwt);

            return new JWTResponseDto(jwt);
        } catch (Exception e) {
            log.error("Error during user registration: {}", e.getMessage(), e);
            throw new RuntimeException("Error during user registration", e);
        }
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JWTResponseDto signIn(JWTRequest request) {
        // Проверка входных данных
        if (request == null || !isValidLoginData(request)) {
            log.warn("Invalid login data: {}", request);
            throw new IllegalArgumentException("Invalid login data");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getLogin(),
                    request.getPassword()
            ));

            UserDetailsImpl userDetails = userService.loadUserByUsername(request.getLogin());
            log.info("User authenticated: {}", userDetails.getUsername());

            String jwt = jwtService.generateToken(userDetails);
            log.debug("JWT token generated: {}", jwt);

            return new JWTResponseDto(jwt);
        } catch (Exception e) {
            log.error("Error during user authentication: {}", e.getMessage(), e);
            throw new RuntimeException("Error during user authentication", e);
        }
    }

    private boolean isValidRegistrationData(JWTRegistrationDto request) {
        return StringUtils.hasText(request.getName())
                && StringUtils.hasText(request.getSurname())
                && StringUtils.hasText(request.getLogin())
                && StringUtils.hasText(request.getPassword());
    }

    private boolean isValidLoginData(JWTRequest request) {
        return StringUtils.hasText(request.getLogin())
                && StringUtils.hasText(request.getPassword());
    }
}



