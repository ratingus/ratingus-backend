package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dnlkk.ratingusbackend.api.model.UserDto;


@Tag(name = "Контроллер профиля", description = "Просмотр и редактирование профиля")
@RequestMapping("/profile")
public interface ProfileApi {
    @Operation(
            summary = "",
            description = ""
    )
    @GetMapping
    ResponseEntity<UserDto> getUser();

    @Operation(
            summary = "",
            description = ""
    )
    @PutMapping
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto);
}
