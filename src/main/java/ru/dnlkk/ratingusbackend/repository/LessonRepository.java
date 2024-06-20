package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.model.Schedule;
import ru.dnlkk.ratingusbackend.model.StudentLesson;
import ru.dnlkk.ratingusbackend.model.UserRole;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query(value = "SELECT l FROM Lesson l WHERE DATE(l.date) IN (:dates)")
    List<Lesson> findByDateIn(@Param("dates") List<Timestamp> timestamps);

    List<Lesson> findByScheduleScheduleForClassIdAndScheduleSubjectId(Integer classId, Integer teacherSubjectId);

    List<Lesson> findByDateBetween(Timestamp startDate, Timestamp endDate);

    @Query("SELECT l FROM Lesson l WHERE l.schedule.scheduleForClass.id = :classId AND DATE(l.date) BETWEEN DATE(:startDate) AND DATE(:endDate)")
    List<Lesson> findByScheduleScheduleForClassIdAndDateBetween(Integer classId, Timestamp startDate, Timestamp endDate);

    List<Schedule> findByScheduleScheduleForClassId(int id);

    List<Lesson> findByScheduleIdInAndDateIn(List<Integer> schedulesIds, List<Timestamp> timestamps);
}
