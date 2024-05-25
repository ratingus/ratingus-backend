package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.LessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.GradeDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.LessonCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.MagazineDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.LessonUpdateDto;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;

import java.util.List;

@Tag(name = "Контроллер журнала", description = "Просмотр и редактирование журнала")
@RequestMapping("/magazine")
public interface MagazineApi {
    @Operation(
            summary = "Журнал учеников",
            description = "Возвращает список учеников, schedule, lesson и lessonStudent для указанных в query-параметрах класса и предмета"
    )
    @GetMapping("/users")
    ResponseEntity<MagazineDto> getMagazineWithUsers(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam(required = true) Integer classId,
            @RequestParam(required = true) Integer teacherSubjectId
    );


    @Operation(
            summary = "Поставить оценку/посещаемость",
            description = "Поставить оценку или посещаемость ученику"
    )
    @PostMapping("/grade")
    /*
        Получаем ученика, дату, scheduleId, lessonId, lessonStudentId
        Проверяем есть ли lesson:
            если есть, то проверяем есть ли lessonStudent:
                если есть, то обновляем его
                если нет, то создаём новый
            если нет, то создаём новый lesson и lessonStudent
     */
    ResponseEntity<GradeDto> createUserGrade(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody GradeDto gradeDto);


    @Operation(
            summary = "Получение уроков",
            description = "Возвращает список уроков для указанных в query-параметрах класса и предмета"
    )
    @GetMapping("/lessons")
    /*
        Получаем список lesson
     */
    ResponseEntity<List<LessonDto>> getMagazineWithLessons(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam(required = true) Integer classId,
            @RequestParam(required = true) Integer teacherSubjectId
    );


    @Operation(
            summary = "Создание урока",
            description = "Создаёт и возвращает урок"
    )
    @PostMapping("/lessons")
    /*
          Создаём новый lesson
     */
    ResponseEntity<List<LessonCreateDto>> createLesson(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody LessonCreateDto lessonCreateDto);


    @Operation(
            summary = "Обновление урока",
            description = "Обновляет и возвращает урок"
    )
    @PutMapping("/lessons")
    ResponseEntity<List<LessonCreateDto>> updateLesson(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody LessonUpdateDto lessonCreateDto);
}
