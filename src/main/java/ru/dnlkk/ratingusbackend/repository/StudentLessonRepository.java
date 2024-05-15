package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.model.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentLessonRepository extends JpaRepository<StudentLesson, Integer> {
    Optional<StudentLesson> findStudentLessonByLessonId(int studentId, int lessonId);
    Optional<List<StudentLesson>> findStudentLessonByStudentIdAndLesson_Date(int studentId, Timestamp date);


}
