package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.ChangeSchoolDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.SetUserCodeDto;
import ru.dnlkk.ratingusbackend.api.model.JWTResponseDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;

import jakarta.servlet.http.HttpServletResponse;


@Tag(name = "Контроллер профиля", description = "Просмотр и редактирование профиля")
@RequestMapping("/profile")
public interface ProfileApi {
    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает информацию о пользователе"
    )
    @GetMapping
    ResponseEntity<UserDto> getUser();


    @Operation(
            summary = "Обновление пользователя",
            description = "Обновляет пользователя и возвращает его"
    )
    @PutMapping
    ResponseEntity<UserDto> updateUser(HttpServletResponse response, @RequestBody UserDto userDto);


    @Operation(
            summary = "Обновление пользователя",
            description = "Обновляет пользователя и возвращает его"
    )
    @PostMapping("/user-code")
    ResponseEntity<JWTResponseDto> setUserCode(HttpServletResponse response, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody SetUserCodeDto userCodeDto);


    @Operation(
            summary = "Обновление пользователя",
            description = "Обновляет пользователя и возвращает его"
    )
    @PostMapping("/change-school")
    ResponseEntity<JWTResponseDto> changeSchool(HttpServletResponse response, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ChangeSchoolDto schoolDto);
}
