package ru.dnlkk.ratingusbackend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dnlkk.ratingusbackend.api.model.DateDto;
import ru.dnlkk.ratingusbackend.api.model.LessonDto;

import java.util.List;

@RequestMapping("/diary")
public interface DiaryApi {
    //todo: мб переделать LessonDto (другую схему сделать)
    //todo: пагинация
    @GetMapping("/week")
    ResponseEntity<List<LessonDto>> getWeekLessons(@RequestBody DateDto dateDto);

    @GetMapping("/day")
    ResponseEntity<List<LessonDto>> getDayLessons(@RequestBody DateDto dateDto);

    @GetMapping("/lesson/{lessonId}")
    ResponseEntity<List<LessonDto>> getLesson(@PathVariable int lessonId);
}
