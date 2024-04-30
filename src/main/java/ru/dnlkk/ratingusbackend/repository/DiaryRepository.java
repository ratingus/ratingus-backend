package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.StudentLesson;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<StudentLesson, Integer> {
    StudentLesson findDiaryByWeek(int weekId);
    StudentLesson findDiaryByDay(int dayId);
    StudentLesson findDiaryByLesson(int lessonId);
}
