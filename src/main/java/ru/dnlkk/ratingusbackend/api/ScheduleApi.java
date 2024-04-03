package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dnlkk.ratingusbackend.api.model.LessonScheduleDto;

import java.util.List;

@Tag(name = "Контроллер расписания", description = "Просмотр расписания")
@RequestMapping("/schedule")
public interface ScheduleApi {
    @GetMapping
    ResponseEntity<List<LessonScheduleDto>> getSchedule(@RequestParam(required = true) String stringClassName);
}
