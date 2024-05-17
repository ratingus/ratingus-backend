package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;

import java.util.List;

@RequestMapping("/admin-panel")
@Tag(name = "Контроллер админ-панели", description = "Управление пользователями учебной организации")
public interface AdminPanelApi {
    @Operation(
            summary = "Получение списка пользователей",
            description = "Возвращает список пользователей учебной организации" //. Доступны query-параметры для поиска по логину/фамилии
    )
    @GetMapping("/user-role")
    ResponseEntity<List<UserRoleDto>> getAllUserRolesForSchool( //todo: дописать @AuthenticationPrincipal ApplicationUser user (секьюрити)
//            @RequestParam(required = false) String surnameOrLogin
    );

    @Operation(
            summary = "Получение списка кодов приглашения",
            description = "Возвращает список не активированных кодов приглашения учебной организации (с пагинацией через query-параметры)"
    )
    @GetMapping("/user-code")
    ResponseEntity<List<UserCodeDto>> getAllUserCodesForSchool();

    @Operation(
            summary = "Создание кода приглашения",
            description = "Создаёт новый код приглашения и возвращает его"
    )
    @PostMapping("/user-code")
    ResponseEntity<UserCodeCreateDto> createUserCode(@RequestBody UserCodeCreateDto userCodeCreateDto);

    @Operation(
            summary = "Пересоздание кода приглашения",
            description = "Создаёт новый код приглашения и возвращает его"
    )
    @PutMapping("/user-code")
    ResponseEntity<UserCodeCreateDto> updateUserCode(@RequestBody UserCodeCreateDto userCodeCreateDto);


    @Operation(
            summary = "Получение списка классов",
            description = "Возвращает список всех классов учебной организации" //. Доступен query-параметр для поиска по названию класса
    )
    @GetMapping("/class") //
    ResponseEntity<List<ClassDto>> getAllClasses(
//            @RequestParam(required = false) String className
    );

    @Operation(
            summary = "Создание нового класса",
            description = "Создаёт новый класс по названию и возвращает его"
    )
    @PostMapping("/class")
    ResponseEntity<ClassDto> createClass(@RequestBody ClassDto classDto);

    @Operation(
            summary = "Удаление класса",
            description = "Удаляет класс по id и возвращает пустой ответ"
    )
    @DeleteMapping("/class/{id}")
    ResponseEntity<Void> deleteClass(@PathVariable("id") Integer id);


    @Operation(
            summary = "Получение расписания длительности уроков",
            description = "Возвращает список уроков с указанными сроками начала и конца"
    )
    @GetMapping("/timetable")
    ResponseEntity<List<TimetableDto>> getTimetable();

    @Operation(
            summary = "Создание нового предмета",
            description = "Создаёт новый предмет по названию и возвращает его"
    )
    @PostMapping("/subject")
    ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto);

}
