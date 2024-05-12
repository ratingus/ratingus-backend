package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.model.Diary;
import ru.dnlkk.ratingusbackend.repository.DiaryRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private DiaryRepository diaryRepository;

    public Diary getDiaryByLessonId(int lessonId) {
        Optional<Diary> optionalDiary = diaryRepository.findDiaryByLessonId(lessonId);
        return optionalDiary.orElse(null);
    }

    public Diary addNote(int diaryId, String note) {
        return diaryRepository.findById(diaryId)
                .map(diary -> {
                    diary.setNote(note);
                    return diary;
                })
                .orElse(null);
    }

    public Diary createDiary(Diary diary) {
        return diaryRepository.saveAndFlush(diary);
    }

    public void deleteDiaryById(int id) {
        diaryRepository.deleteById(id);
    }
}
