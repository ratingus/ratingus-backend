package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.ProfileApi;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.profile.EditProfileDto;
import ru.dnlkk.ratingusbackend.api.dtos.profile.ProfileDto;
import ru.dnlkk.ratingusbackend.api.dtos.profile.SchoolDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.ChangeSchoolDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.SetUserCodeDto;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.UserCode;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;
import ru.dnlkk.ratingusbackend.repository.UserCodeRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;
import ru.dnlkk.ratingusbackend.repository.UserRoleRepository;
import ru.dnlkk.ratingusbackend.security.JwtTokenService;

import jakarta.servlet.http.HttpServletResponse;
import ru.dnlkk.ratingusbackend.service.UserService;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProfileController extends ExceptionHandlerController implements ProfileApi {
    private final JwtTokenService jwtTokenService;
    private final SchoolRepository schoolRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserCodeRepository userCodeRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public ResponseEntity<ProfileDto> getUser(Integer id) {
        ProfileDto profileDto = new ProfileDto();

        var user = userRepository.findById(id).orElseThrow();
        profileDto.setId(user.getId());
        profileDto.setLogin(user.getLogin());
        profileDto.setName(user.getName());
        profileDto.setSurname(user.getSurname());
        profileDto.setPatronymic(user.getPatronymic());
        profileDto.setBirthdate(user.getBirthDate());
        profileDto.setSchools(user.getUsersRoles().stream().map(userRole -> {
            School school = userRole.getSchool();
            return new SchoolDto(school.getId(), school.getName(), userRole.getRole(), userRole.getRoleClass() == null ? null : new ClassDto(userRole.getRoleClass().getId(), userRole.getRoleClass().getName()));
        }).toList());

        return ResponseEntity.ok(profileDto);
    }

    @Override
    public ResponseEntity<ProfileDto> getUser(UserDetailsImpl userDetails) {
        ProfileDto profileDto = new ProfileDto();

        var user = userRepository.findById(userDetails.getUser().getId()).orElseThrow();
        profileDto.setId(user.getId());
        profileDto.setLogin(user.getLogin());
        profileDto.setName(user.getName());
        profileDto.setSurname(user.getSurname());
        profileDto.setPatronymic(user.getPatronymic());
        profileDto.setBirthdate(user.getBirthDate());
        profileDto.setSchools(user.getUsersRoles().stream().map(userRole -> {
            School school = userRole.getSchool();
            return new SchoolDto(school.getId(), school.getName(), userRole.getRole(), userRole.getRoleClass() == null ? null : new ClassDto(userRole.getRoleClass().getId(), userRole.getRoleClass().getName()));
        }).toList());

        return ResponseEntity.ok(profileDto);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(HttpServletResponse response, UserDetailsImpl userDetails, EditProfileDto userDto) {
        userService.updateUser(userDetails, userDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity setUserCode(HttpServletResponse response, UserDetailsImpl userDetails, SetUserCodeDto userCodeDto) {
        UserCode code = userCodeRepository.findUserCodesByCode(userCodeDto.getCode());

        if (code == null) {
            return ResponseEntity.badRequest().body("Код не найден");
        }

        if (code.isActivated()) {
            return ResponseEntity.badRequest().body("Код уже активирован");
        }

        if (code.getRole() == Role.STUDENT){
            Optional<UserRole> checkUserRole = userRoleRepository.findByUserAndRoleAndSchoolId(userDetails.getUser(), Role.STUDENT, code.getSchool().getId());
            if (checkUserRole.isPresent()) {
                return ResponseEntity.badRequest().body("Ученик не может иметь больше 1 класса в одной учебной организации");
            }
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
        code.setUser(userRole);
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
        userRole.setLastLogin(new Timestamp(System.currentTimeMillis() + 1000));
        userRoleRepository.save(userRole);
        String token = jwtTokenService.generateToken(userDetails);
        response.setHeader("Set-Cookie", "token=" + token + "; HttpOnly; Secure; SameSite=Strict");
        return ResponseEntity.noContent().build();
    }
}
