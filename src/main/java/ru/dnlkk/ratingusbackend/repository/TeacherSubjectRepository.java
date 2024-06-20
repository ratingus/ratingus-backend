package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.TeacherSubject;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Integer> {

    Optional<TeacherSubject> findTeacherSubjectsBySubjectIdAndTeacherId(int subjectId, int teacherId);
    @Query("SELECT ts FROM TeacherSubject ts WHERE ts.subject.id IN :subjectIds")
    List<TeacherSubject> findBySubjectIds(@Param("subjectIds") List<Integer> subjectIds);
}
