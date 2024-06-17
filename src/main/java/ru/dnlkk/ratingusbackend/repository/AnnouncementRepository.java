package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.model.Class;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    List<Announcement> findByClasses_Id(int classId, PageRequest pageRequest);
    List<Announcement> getAnnouncementsByClassesIn(List<Class> classes, PageRequest pageRequest);

    List<Announcement> findByCreatorSchoolId(int schoolId, PageRequest of);

//    @Query("SELECT a FROM Announcement a WHERE ?1 in a.classes")
//    List<Announcement> findByClass(String filter);
}
