package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.ProfileApi;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.ChangeSchoolDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.SetUserCodeDto;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.UserCode;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;
import ru.dnlkk.ratingusbackend.repository.UserCodeRepository;
import ru.dnlkk.ratingusbackend.repository.UserRoleRepository;
import ru.dnlkk.ratingusbackend.security.JwtTokenService;

import jakarta.servlet.http.HttpServletResponse;

import java.sql.Timestamp;

@RestController
@AllArgsConstructor
public class ProfileController implements ProfileApi {
    private final JwtTokenService jwtTokenService;
    private final SchoolRepository schoolRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserCodeRepository userCodeRepository;

    @Override
    public ResponseEntity<UserDto> getUser() {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> updateUser(HttpServletResponse response, UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity setUserCode(HttpServletResponse response, UserDetailsImpl userDetails, SetUserCodeDto userCodeDto) {
        UserCode code = userCodeRepository.findUserCodesByCode(userCodeDto.getCode());

        if (code.isActivated()) {
            return ResponseEntity.badRequest().body("Код уже активирован");
        }

        UserRole userRole = new UserRole();
        userRole.setUser(userDetails.getUser());
        userRole.setSchool(code.getSchool());
        userRole.setName(code.getName());
        userRole.setSurname(code.getSurname());
        userRole.setPatronymic(code.getPatronymic());
        userRole.setRole(code.getRole());
        userRole.setRoleClass(code.getUserClass());
        userRole.setLastLogin(new Timestamp(System.currentTimeMillis()));

        userDetails.setUserRole(userRole);
        String token = jwtTokenService.generateToken(userDetails);

        userRoleRepository.save(userRole);
        code.setActivated(true);
        userCodeRepository.save(code);
        response.addHeader("Set-Cookie", "token=" + token + "; HttpOnly; Secure; SameSite=Strict");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity changeSchool(HttpServletResponse response, UserDetailsImpl userDetails, ChangeSchoolDto schoolDto) {
        School school = schoolRepository.findById(schoolDto.getId()).orElse(null);
        if (school == null) {
            return ResponseEntity.badRequest().body("Школа не найдена");
        }
        UserRole userRole = userRoleRepository.findUserRoleByUserAndSchool(userDetails.getUser(), school);
        if (userRole == null) {
            return ResponseEntity.badRequest().body("Пользователь не состоит в этой школе");
        }
        userDetails.setUserRole(userRole);
        String token = jwtTokenService.generateToken(userDetails);
        response.addHeader("Set-Cookie", "token=" + token + "; HttpOnly; Secure; SameSite=Strict");
        return ResponseEntity.noContent().build();
    }
}
