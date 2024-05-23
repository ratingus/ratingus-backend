package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Lesson;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByDateIn(List<Timestamp> timestamps);
}
