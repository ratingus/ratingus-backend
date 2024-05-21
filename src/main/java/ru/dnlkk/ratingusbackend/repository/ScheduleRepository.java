package ru.dnlkk.ratingusbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.Schedule;
import ru.dnlkk.ratingusbackend.model.TeacherSubject;

import java.util.List;
import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT ts FROM Schedule s JOIN s.subject ts WHERE s.scheduleForClass.id = :classId")
    Set<TeacherSubject> findTeacherSubjectsByClassId(@Param("classId") int classId);

    List<Schedule> findByScheduleForClass(Class clazz);

    List<Schedule> findByScheduleForClassAndDayOfWeek(Class clazz, int dayOfWeek);

    Schedule findByScheduleForClassAndDayOfWeekAndTimetableLessonNumber(Class clazz, int dayOfWeek, int lessonNumber);
}
