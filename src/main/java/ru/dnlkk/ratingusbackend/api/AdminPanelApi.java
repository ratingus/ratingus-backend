package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
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
    );

    @Operation(
            summary = "Получение списка кодов приглашения",
            description = "Возвращает список не активированных кодов приглашения учебной организации"
    )
    @GetMapping("/user-code")
    ResponseEntity<List<UserCodeWithClassDto>> getAllUserCodesForSchool();

    @Operation(
            summary = "Создание кода приглашения",
            description = "Создаёт новый код приглашения и возвращает его"
    )
    @PostMapping("/user-code")
    ResponseEntity<UserCodeWithClassDto> createUserCode(
            @Schema(description = "DTO создаваемого кода приглашения")
            @RequestBody UserCodeWithClassDto userCodeWithClassDto
    );

    @Operation(
            summary = "Пересоздание кода приглашения",
            description = "Создаёт новый код приглашения и возвращает его"
    )
    @PutMapping("/user-code/{id}")
    ResponseEntity<UserCodeWithClassDto> updateUserCode(
            @PathVariable("id") int id,
            @Schema(description = "DTO обновляемого кода приглашения (если задать для code значение null, то будет сгенерирован новый код")
            @RequestBody UserCodeWithClassDto userCodeWithClassDto
    );


    @Operation(
            summary = "Получение списка классов",
            description = "Возвращает список всех классов учебной организации"
    )
    @GetMapping("/class")
    ResponseEntity<List<ClassDto>> getAllClasses();

    @Operation(
            summary = "Создание нового класса",
            description = "Создаёт новый класс по названию и возвращает его"
    )
    @PostMapping("/class")
    ResponseEntity<ClassDto> createClass(
            @Schema(description = "DTO создаваемого класса")
            @RequestBody ClassDto classDto
    );

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
    ResponseEntity<SubjectDto> createSubject(
            @Schema(description = "DTO создаваемого предмета")
            @RequestBody SubjectCreateDto subjectDto
    );

    @Operation(
            summary = "Привязка к предмету учителя",
            description = "Обновляет предмет, добавляя к нему учителя, и возвращает его"
    )
    @PostMapping("/subject-teacher")
    ResponseEntity<TeacherSubjectDto> setTeacherToSubject(
            @Schema(description = "DTO связываемых предмета с учителем")
            @RequestBody TeacherSubjectCreateDto teacherSubjectCreateDto
    );
}
