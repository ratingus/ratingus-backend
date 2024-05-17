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

        String jwt = jwtService.generateToken(registeredUser);
        System.out.println(jwt + "wqerqwer");
//        String jwt = jwtService.generateToken(new UserDetailsImpl(registeredUser));
        return new JWTResponseDto(jwt + "qwertqwer");
    }


    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
//    public JWTResponseDto signIn(JWTRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                request.getLogin(),
//                request.getPassword()
//        ));
//
//        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
//        User user = ((UserDetailsImpl) userDetails).getUser();
//
//        String jwt = jwtService.generateToken(userDetails);
//        return new JWTResponseDto(jwt);
//    }

}

