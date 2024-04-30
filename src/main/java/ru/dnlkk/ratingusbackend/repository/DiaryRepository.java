package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.api.dtos.DayLessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.WeekLessonDto;
import ru.dnlkk.ratingusbackend.model.StudentLesson;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<StudentLesson, Integer> {
    WeekLessonDto findDiaryByWeek(int weekNumber);
    DayLessonDto findDiaryByDay(int weekNumber, int dayNumber);
    StudentLesson findDiaryByLesson(int lessonId);
}
