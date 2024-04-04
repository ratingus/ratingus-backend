package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            summary = "Получение списка пользователей",
            description = "Возвращает список пользователей учебной организации (с пагинацией через query-параметры). Доступны query-параметры для поиска по логину или названию класса"
    )
    @GetMapping("/users")
    ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(required = false) String surnameOrLogin,
            @RequestParam(required = false) String className,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "25") Integer limit
    );


    @Operation(
            summary = "Получение списка кодов приглашения",
            description = "Возвращает список не активированных кодов приглашения учебной организации (с пагинацией через query-параметры)"
    )
    @GetMapping("/user-codes")
    ResponseEntity<List<UserCodeDto>> getAllUserCodes(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "25") Integer limit
    );


    @Operation(
            summary = "Создание кода приглашения",
            description = "Создаёт новый код приглашения и возвращает его"
    )
    @PostMapping("/users")
    ResponseEntity<UserCodeDto> createUserCode(@RequestBody UserCodeDto userCodeDto);


    @Operation(
            summary = "Получение списка классов",
            description = "Возвращает список всех классов учебной организации (с пагинацией через query-параметры). Доступен query-параметр для поиска по названию класса"
    )
    @GetMapping("/classes") //
    ResponseEntity<List<ClassDto>> getAllClasses(
            @RequestParam(required = false) String className,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "25") Integer limit
    );


    @Operation(
            summary = "Создание нового класса",
            description = "Создаёт новый класс по названию и возвращает его"
    )
    @PostMapping("/classes")
    ResponseEntity<ClassDto> createClass(@RequestBody ClassDto classDto);


    @Operation(
            summary = "Получение расписания длительности уроков",
            description = "Возвращает список уроков с указанными сроками начала и конца"
    )
    @GetMapping("/others")
    ResponseEntity<List<TimetableDto>> getDurationOfLessons();


    //todo: ЧТБЫ ПОСТАВИТЬ ОЦЕНКУ, нужны айди класса и айди ученика и айди предмета (вдруг кто-то одновременно в двух классах будет потом)

    //todo: доделать для менеджера платформы
}
