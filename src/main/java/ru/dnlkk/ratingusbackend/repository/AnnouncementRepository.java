package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Announcement;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    List<Announcement> findByClasses_Id(int classId, PageRequest pageRequest);
    List<Announcement> findByClasses_Id(int classId);

//    @Query("SELECT a FROM Announcement a WHERE ?1 in a.classes")
//    List<Announcement> findByClass(String filter);
}
