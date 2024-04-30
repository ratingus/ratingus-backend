package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.api.dtos.DayLessonDto;
import ru.dnlkk.ratingusbackend.api.dtos.WeekLessonDto;
import ru.dnlkk.ratingusbackend.model.StudentLesson;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<StudentLesson, Integer> {
//    Optional<WeekLessonDto> findDiaryByWeek(int weekNumber);
////    Optional<DayLessonDto> findDiaryByDay(int weekNumber, int dayNumber);
    Optional<StudentLesson> findDiaryByLessonId(int lessonId);

//    List<StudentLesson> findAllByStudentIdAndLesson_Date_Day(int userId, Timestamp day);

    @Query("SELECT se FROM StudentLesson se WHERE se.student.id = :id and se.lesson.date BETWEEN :startOfday and :endOfDay")
    List<StudentLesson> findDiaryByDay(int id, Timestamp startOfday, Timestamp endOfDay);
}
