package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.model.School;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findLessonsByClassName(String className, PageRequest pageRequest);
}
