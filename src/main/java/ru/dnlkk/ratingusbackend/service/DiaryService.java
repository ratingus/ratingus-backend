package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.DayLessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.WeekLessonDto;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.repository.DiaryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private DiaryRepository diaryRepository;

    public WeekLessonDto getDiaryByWeek(int weekId){
        Optional<WeekLessonDto> optionalDiary = diaryRepository.findDiaryByWeek(weekId);
        return optionalDiary.orElse(null);
    }

    private DayLessonDto getDiaryByDay(int weekNumber, int dayNumber){
        Optional<DayLessonDto> optionalDiary = diaryRepository.findDiaryByDay(weekNumber, dayNumber);
        return optionalDiary.orElse(null);
    }

    public StudentLesson getDiaryByLessonId(int lessonId) {
        Optional<StudentLesson> optionalDiary = diaryRepository.findDiaryByLesson(lessonId);
        return optionalDiary.orElse(null);
    }

    public StudentLesson addNote(int diaryId, String note) {
        return diaryRepository.findById(diaryId)
                .map(diary -> {
                    diary.setNote(note);
                    return diary;
                })
                .orElse(null);
    }

    public StudentLesson createDiary(StudentLesson studentLesson) {
        return diaryRepository.saveAndFlush(studentLesson);
    }

    public void deleteDiaryById(int id) {
        diaryRepository.deleteById(id);
    }
}
