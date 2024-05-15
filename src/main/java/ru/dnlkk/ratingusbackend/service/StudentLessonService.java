package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.repository.StudentLessonRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentLessonService {
    private StudentLessonRepository studentLessonRepository;

    public StudentLesson getStudentLessonByLessonId(int studentId, int lessonId) {
        Optional<StudentLesson> optionalDiary = studentLessonRepository.findStudentLessonByLessonId(studentId, lessonId);
        return optionalDiary.orElse(null);
    }

//    public List<StudentLesson> getStudentLessonsByDay(int studentId, int weekOfYear){
//
//        Optional<StudentLesson> optionalDiary = studentLessonRepository.findStudentLessonByLessonId(studentId);
//        return
//    }

    public StudentLesson addNote(int diaryId, String note) {
        return studentLessonRepository.findById(diaryId)
                .map(diary -> {
                    diary.setNote(note);
                    return diary;
                })
                .orElse(null);
    }

    public StudentLesson createDiary(StudentLesson studentLesson) {
        return studentLessonRepository.saveAndFlush(studentLesson);
    }

    public void deleteDiaryById(int id) {
        studentLessonRepository.deleteById(id);
    }
}
