package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.DayLessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.WeekLessonDto;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.repository.DiaryRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private DiaryRepository diaryRepository;

//    public WeekLessonDto getDiaryByWeek(int weekId){
//        Optional<WeekLessonDto> optionalDiary = diaryRepository.findDiaryByWeek(weekId);
//        return optionalDiary.orElse(null);
//    }

    private List<StudentLesson> getDiaryByDay(int userId, Timestamp dayNumber){
        LocalDate.of(24, 3,4);
       ;
        List<StudentLesson> diary = diaryRepository.findDiaryByDay(userId,  Timestamp.from(LocalDate.of(24,
                3,4).atStartOfDay().toInstant(ZoneOffset.ofHours(3))),  Timestamp.from(LocalDate.of(24, 3,4).atStartOfDay().toInstant(ZoneOffset.ofHours(6))));
        return diary;
    }

    public StudentLesson getDiaryByLessonId(int lessonId) {
        Optional<StudentLesson> optionalDiary = diaryRepository.findDiaryByLessonId(lessonId);
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
