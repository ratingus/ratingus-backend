package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Diary;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    Optional<Diary> findDiaryByLessonId(int lessonId);

}
