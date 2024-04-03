package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dnlkk.ratingusbackend.api.model.DateDto;
import ru.dnlkk.ratingusbackend.api.model.LessonDto;

import java.util.List;

@Tag(name = "Контроллер дневника", description = "Просмотр оценок, домашнего задания")
@RequestMapping("/diary")
public interface DiaryApi {
    //todo: мб переделать LessonDto (другую схему сделать)
    //todo: пагинация
    @Operation(
            summary = "",
            description = ""
    )
    @GetMapping("/week")
    ResponseEntity<List<LessonDto>> getWeekLessons(@RequestBody DateDto dateDto);

    @Operation(
            summary = "",
            description = ""
    )
    @GetMapping("/day")
    ResponseEntity<List<LessonDto>> getDayLessons(@RequestBody DateDto dateDto);

    @Operation(
            summary = "",
            description = ""
    )
    @GetMapping("/lesson/{lessonId}")
    ResponseEntity<List<LessonDto>> getLesson(@PathVariable int lessonId);
}
