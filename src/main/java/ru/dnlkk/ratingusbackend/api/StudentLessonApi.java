package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.DateDto;
import ru.dnlkk.ratingusbackend.api.dtos.magazine.LessonCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.NoteDto;

import java.util.List;

@Tag(name = "Контроллер дневника", description = "Просмотр оценок, домашнего задания")
@RequestMapping("/diary")
public interface StudentLessonApi {
    @Operation(
            summary = "Получение уроков за неделю",
            description = "Возвращает список всех уроков за неделю по указанной дате"
    )
    @GetMapping("/week")
    ResponseEntity<List<LessonCreateDto>> getWeekLessons(@RequestBody DateDto dateDto);


    @Operation(
            summary = "Получение уроков за день",
            description = "Возвращает список всех уроков за день по указанной дате"
    )
    @GetMapping("/day")
    ResponseEntity<List<LessonCreateDto>> getDayLessons(@RequestBody DateDto dateDto);

    //todo: нет работы с заметками

    @Operation(
            summary = "Получение урока",
            description = "Возвращает информацию об уроке по его id"
    )
    @GetMapping("/lesson/{lessonId}")
    ResponseEntity<List<LessonCreateDto>> getLesson(@PathVariable int lessonId);


    @Operation(
            summary = "Создание заметки",
            description = "Создаёт и возвращает заметку"
    )
    @PostMapping("/lesson/{lessonId}") //todo: подумать, может, lessonId не делать в NoteDto
    ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto);


    @Operation(
            summary = "Обновление заметки",
            description = "Обновляет и возвращает заметку"
    )
    @PutMapping("/lesson/{lessonId}") //todo: подумать, может, lessonId не делать в NoteDto
    ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto);


    @Operation(
            summary = "Удаление заметки",
            description = "Удаляет заметку и ничего не возвращает"
    )
    @PutMapping("/lesson/{lessonId}") //todo: подумать, может, lessonId не делать в NoteDto
    ResponseEntity<Void> deleteNote();
}
