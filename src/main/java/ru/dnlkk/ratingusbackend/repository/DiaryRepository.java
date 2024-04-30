package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.api.dtos.DayLessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.WeekLessonDto;
import ru.dnlkk.ratingusbackend.model.StudentLesson;

import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<StudentLesson, Integer> {
    Optional<WeekLessonDto> findDiaryByWeek(int weekNumber);
    Optional<DayLessonDto> findDiaryByDay(int weekNumber, int dayNumber);
    Optional<StudentLesson> findDiaryByLesson(int lessonId);
}
