package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.model.JWTRegistrationDto;
import ru.dnlkk.ratingusbackend.api.model.JWTRequest;
import ru.dnlkk.ratingusbackend.api.model.JWTResponseDto;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.security.JwtTokenService;

@Service
@RequiredArgsConstructor
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
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPatronymic(request.getPatronymic());
        user.setBirthDate(request.getBirthDate());
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User registeredUser = userService.registerUser(user);
        System.out.println(registeredUser);
//        String jwt = jwtService.generateToken((UserDetails) registeredUser);
//        System.out.println(jwt + "wqerqwer");
        String jwt = jwtService.generateToken(new UserDetailsImpl(registeredUser));
        return new JWTResponseDto(jwt);
    }


    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JWTResponseDto signIn(JWTRequest request) {
        System.out.println("1");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        System.out.println("2");
        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        System.out.println(userDetails);
        String jwt = jwtService.generateToken(userDetails);
        System.out.println(jwt);
        return new JWTResponseDto(jwt);
    }

}

