package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.DiaryApi;
import ru.dnlkk.ratingusbackend.api.dtos.NoteDto;
import ru.dnlkk.ratingusbackend.api.dtos.diary.DayLessonsDto;
import ru.dnlkk.ratingusbackend.api.dtos.diary.LessonDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.DiaryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController implements DiaryApi {
    private final DiaryService diaryService;

    @Override
    public ResponseEntity<List<DayLessonsDto>> getWeekLessons(UserDetailsImpl userDetails, Integer week) {
        return ResponseEntity.ok(diaryService.getWeekLessons(userDetails.getUserRole(), week));
    }

    @Override
    public ResponseEntity<DayLessonsDto> getDayLessons(UserDetailsImpl userDetails, Integer week, Integer day) {
        return null;
    }

    @Override
    public ResponseEntity<LessonDto> getLesson(UserDetailsImpl userDetails, Integer week, Integer day, Integer lessonId) {
        return null;
    }

    @Override
    public ResponseEntity createNote(UserDetailsImpl userDetails, NoteDto noteDto) {
        diaryService.createNote(userDetails.getUserRole(), noteDto);
        return ResponseEntity.noContent().build();
    }
}
