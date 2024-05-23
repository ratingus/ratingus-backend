package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.MagazineApi;
import ru.dnlkk.ratingusbackend.api.dtos.GradeDto;
import ru.dnlkk.ratingusbackend.api.dtos.LessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.MagazineDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.MagazineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MagazineController implements MagazineApi {
    private final MagazineService magazineService;

    @Override
    public ResponseEntity<MagazineDto> getMagazineWithUsers(UserDetailsImpl user, Integer classId, Integer teacherSubjectId) {
        return ResponseEntity.ok(magazineService.getMagazineWithUsers(user, classId, teacherSubjectId));
    }

    @Override
    public ResponseEntity<GradeDto> createUserGrade(GradeDto gradeDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<LessonDto>> getMagazineWithLessons(String className, String subjectName) {
        return null;
    }

    @Override
    public ResponseEntity<List<LessonDto>> createLesson(LessonDto lessonDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<LessonDto>> updateLesson(LessonDto lessonDto) {
        return null;
    }
}
