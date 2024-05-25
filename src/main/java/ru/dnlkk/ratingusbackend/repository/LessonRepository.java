package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Lesson;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query(value = "SELECT l FROM Lesson l WHERE DATE(l.date) IN (:dates)")
    List<Lesson> findByDateIn(@Param("dates") List<Timestamp> timestamps);

    List<Lesson> findByScheduleScheduleForClassIdAndScheduleSubjectId(Integer classId, Integer teacherSubjectId);
}
