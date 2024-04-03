package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.model.ClassDto;
import ru.dnlkk.ratingusbackend.api.model.TimetableDto;
import ru.dnlkk.ratingusbackend.api.model.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.model.UserDto;

import java.util.List;

@Tag(name = "Контроллер админ-панели", description = "Управление пользователями учебной организации")
@RequestMapping("/admin-panel")
public interface AdminPanelApi {
    @GetMapping("/users") //todo: поиск по классу
    ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(required = false) String stringSurnameOrLogin,
            @RequestParam(required = false) String stringClassName
    );

    @PostMapping("/users")
    ResponseEntity<UserCodeDto> createUserCode(@RequestBody UserCodeDto userCodeDto);

    @GetMapping("/classes") //todo: поиск по классу
    ResponseEntity<List<ClassDto>> getAllClasses(@RequestParam(required = false) String stringClassName);

    @PostMapping("/classes")
    ResponseEntity<UserCodeDto> createClass(@RequestBody ClassDto classDto);

    @GetMapping("/others")
    ResponseEntity<List<TimetableDto>> getDurationOfLessons();
}
