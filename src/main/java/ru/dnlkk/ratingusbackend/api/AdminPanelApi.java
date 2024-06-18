package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectsDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleSimpleDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;

import java.util.List;

@RequestMapping("/admin-panel")
@Tag(name = "Контроллер админ-панели", description = "Управление пользователями учебной организации")
public interface AdminPanelApi {

    @Operation(
            summary = "Получение списка пользователей",
            description = "Возвращает список пользователей учебной организации"
    )
    @GetMapping("/user-role")
    ResponseEntity<List<UserRoleDto>> getAllUserRolesForSchool(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(
            summary = "Получение списка учителей",
            description = "Возвращает список учителей учебной организации"
    )
    @GetMapping("/teacher")
    ResponseEntity<List<UserRoleSimpleDto>> getAllTeachersForSchool(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(
            summary = "Получение списка кодов приглашения",
            description = "Возвращает список не активированных кодов приглашения учебной организации"
    )
    @GetMapping("/user-code")
    ResponseEntity<List<UserCodeWithClassDto>> getAllUserCodesForSchool(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(
            summary = "Создание кода приглашения",
            description = "Создаёт новый код приглашения и возвращает его"
    )
    @PostMapping("/user-code")
    ResponseEntity<UserCodeWithClassDto> createUserCode(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "DTO создаваемого кода приглашения")
            @RequestBody UserCodeWithClassDto userCodeWithClassDto
    );

    @Operation(
            summary = "Пересоздание кода приглашения",
            description = "Создаёт новый код приглашения и возвращает его"
    )
    @PutMapping("/user-code/{id}")
    ResponseEntity<UserCodeWithClassDto> updateUserCode(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "id пересоздаваемого кода приглашения")
            @PathVariable("id") int id,

            @Schema(description = "DTO обновляемого кода приглашения (если задать для code значение null, то будет сгенерирован новый код")
            @RequestBody UserCodeWithClassDto userCodeWithClassDto
    );


    @Operation(
            summary = "Получение списка классов",
            description = "Возвращает список всех классов учебной организации"
    )
    @GetMapping("/class")
    ResponseEntity<List<ClassDto>> getAllClasses(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(
            summary = "Создание нового класса",
            description = "Создаёт новый класс по названию и возвращает его"
    )
    @PostMapping("/class")
    ResponseEntity<ClassDto> createClass(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "DTO создаваемого класса")
            @RequestBody ClassDto classDto
    );

    @Operation(
            summary = "Обновить название класса",
            description = "Обновляет класс"
    )
    @PutMapping("/class/{id}")
    ResponseEntity<ClassDto> updateClass(
            @PathVariable("id") int id,
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "DTO обновляемого класса")
            @RequestBody ClassDto classDto
    );

    @Operation(
            summary = "Удаление класса",
            description = "Удаляет класс по id и возвращает пустой ответ"
    )
    @DeleteMapping("/class/{id}")
    ResponseEntity<Void> deleteClass(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "id удаляемого класса")
            @PathVariable("id") Integer id
    );


    @Operation(
            summary = "Получение расписания длительности уроков",
            description = "Возвращает список уроков с указанными сроками начала и конца"
    )
    @GetMapping("/timetable")
    ResponseEntity<List<TimetableDto>> getTimetable(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(
            summary = "Получение всех предметов",
            description = "Возвращает список всех предметов учебной организации"
    )
    @GetMapping("/subject")
    ResponseEntity<List<TeacherSubjectsDto>> getAllSubjects(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );

    @Operation(
            summary = "Создание нового предмета",
            description = "Создаёт новый предмет по названию и возвращает его"
    )
    @PostMapping("/subject")
    ResponseEntity<SubjectDto> createSubject(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "DTO создаваемого предмета")
            @RequestBody SubjectCreateDto subjectDto
    );

    @Operation(
            summary = "Обновить название предмета",
            description = "Обновляет предмет"
    )
    @PutMapping("/subject/{id}")
    ResponseEntity<SubjectDto> updateSubject(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("id") int id,

            @Schema(description = "DTO создаваемого предмета")
            @RequestBody SubjectCreateDto subjectDto
    );

    @Operation(
            summary = "Привязка к предмету учителя",
            description = "Обновляет предмет, добавляя к нему учителя, и возвращает его"
    )
    @PostMapping("/subject-teacher")
    ResponseEntity<TeacherSubjectDto> setTeacherToSubject(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "DTO связываемых предмета с учителем")
            @RequestBody TeacherSubjectCreateDto teacherSubjectCreateDto
    );

    @Operation(
            summary = "Удаление привязки учителя к предмету",
            description = "Удаляет связь предмета с учителем, который его ведёт"
    )
    @DeleteMapping("/subject-teacher/{id}")
    ResponseEntity<TeacherSubjectDto> deleteTeacherToSubject(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "id удаляемой связи предмета с учителем")
            @PathVariable(name = "id") int teacherSubjectId
    );
}
