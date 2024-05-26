package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.diary.DayLessonsDto;
import ru.dnlkk.ratingusbackend.api.dtos.diary.LessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.NoteDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;

import java.util.List;

@Tag(name = "Контроллер дневника", description = "Просмотр оценок, домашнего задания")
@RequestMapping("/diary")
public interface DiaryApi {
    @Operation(
            summary = "Получение уроков за неделю",
            description = "Возвращает список всех уроков за неделю по указанной дате"
    )
    @GetMapping("/week")
    ResponseEntity<List<DayLessonsDto>> getWeekLessons(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("week") Integer week);


    @Operation(
            summary = "Получение уроков за день",
            description = "Возвращает список всех уроков за день по указанной дате"
    )
    @GetMapping("/day")
    ResponseEntity<DayLessonsDto> getDayLessons(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("week") Integer week, @RequestParam("day") Integer day);

    @Operation(
            summary = "Получение урока",
            description = "Возвращает информацию об уроке по его id"
    )
    @GetMapping("/lesson")
    ResponseEntity<LessonDto> getLesson(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("week") Integer week, @RequestParam("day") Integer day, @RequestParam("lesson") Integer lessonId);


    @Operation(
            summary = "Создание заметки",
            description = "Создаёт заметку"
    )
    @PostMapping("/lesson")
    ResponseEntity createNote(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody NoteDto noteDto);
}
