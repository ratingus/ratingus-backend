package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.AttendanceDto;
import ru.dnlkk.ratingusbackend.api.dtos.GradeDto;
import ru.dnlkk.ratingusbackend.api.dtos.LessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.MagazineDto;

import java.util.List;

@Tag(name = "Контроллер журнала", description = "Просмотр и редактирование журнала")
@RequestMapping("/magazine")
public interface MagazineApi {
    @Operation(
            summary = "Получение пользователей",
            description = "Возвращает список пользователей для указанных в query-параметрах класса и предмета"
    )
    @GetMapping("/users")
    ResponseEntity<List<MagazineDto>> getMagazineWithUsers(
            @RequestParam(required = true) String className,
            @RequestParam(required = true) String subjectName
    );


    @Operation(
            summary = "Создание оценки",
            description = "Создаёт оценку для пользователя и возвращает её"
    )
    @PostMapping("/users-grade")
    ResponseEntity<GradeDto> createUserGrade(@RequestBody GradeDto gradeDto);


    @Operation(
            summary = "Обновление оценки",
            description = "Обновляет оценку для пользователя и возвращает её"
    )
    @PutMapping("/users-grade")
    ResponseEntity<GradeDto> updateUserGrade(@RequestBody GradeDto gradeDto);


    @Operation(
            summary = "Обновление посещаемости",
            description = "Обновляет посещаемость для пользователя и возвращает её"
    )
    @PutMapping("/users-attendance")
    ResponseEntity<GradeDto> updateUserAttendance(@RequestBody AttendanceDto attendanceDto);



    @Operation(
            summary = "Получение уроков",
            description = "Возвращает список уроков для указанных в query-параметрах класса и предмета"
    )
    @GetMapping("/lessons")
    ResponseEntity<List<LessonDto>> getMagazineWithLessons(
            @RequestParam(required = true) String className,
            @RequestParam(required = true) String subjectName
    );


    @Operation(
            summary = "Создание урока",
            description = "Создаёт и возвращает урок"
    )
    @PostMapping("/lessons")
    ResponseEntity<List<LessonDto>> createLesson(@RequestBody LessonDto lessonDto);


    @Operation(
            summary = "Обновление урока",
            description = "Обновляет и возвращает урок"
    )
    @PutMapping("/lessons")
    ResponseEntity<List<LessonDto>> updateLesson(@RequestBody LessonDto lessonDto);
}
