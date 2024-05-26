package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.model.UserRole;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StudentLessonRepository  extends JpaRepository<StudentLesson, Integer> {
    StudentLesson findByLessonIdAndStudentId(int id, int id1);

    List<StudentLesson> findByStudentAndLessonDateBetween(UserRole userRole, Timestamp startDate, Timestamp endDate);
}
