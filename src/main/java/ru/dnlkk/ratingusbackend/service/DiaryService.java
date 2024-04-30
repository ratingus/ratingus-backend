package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.DayLessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.WeekLessonDto;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.repository.DiaryRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private DiaryRepository diaryRepository;

    public WeekLessonDto getDiaryByWeek(int weekId){
        return diaryRepository.findDiaryByWeek(weekId);
    }

    private DayLessonDto getLessonByDay(int weekNumber, int dayNumber){
        return diaryRepository.findDiaryByDay(weekNumber, dayNumber);
    }

    public StudentLesson getDiaryByLessonId(int lessonId) {
        return diaryRepository.findDiaryByLesson(lessonId);
    }

    public StudentLesson addNote(int diaryId, String note) {
        return diaryRepository.findById(diaryId)
                .map(diary -> {
                    diary.setNote(note);
                    return diary;
                })
                .orElseThrow(() -> new NoSuchElementException("Дневник отсутствует"));
    }

    public StudentLesson createDiary(StudentLesson studentLesson) {
        return diaryRepository.saveAndFlush(studentLesson);
    }

    public void deleteDiaryById(int id) {
        diaryRepository.deleteById(id);
    }
}
