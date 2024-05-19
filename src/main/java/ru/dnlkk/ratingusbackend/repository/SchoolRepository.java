package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.model.School;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
    School findSchoolById(int i);
}
