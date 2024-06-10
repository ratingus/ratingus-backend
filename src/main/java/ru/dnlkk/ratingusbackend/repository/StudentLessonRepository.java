package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.model.UserRole;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StudentLessonRepository  extends JpaRepository<StudentLesson, Integer> {
    StudentLesson findByLessonIdAndStudentId(int id, int id1);


    @Query("SELECT sl FROM StudentLesson sl WHERE sl.student = :student AND DATE(sl.lesson.date) BETWEEN DATE(:startDate) AND DATE(:endDate)")
    List<StudentLesson> findByStudentAndLessonDateBetween(UserRole student, Timestamp startDate, Timestamp endDate);
}
