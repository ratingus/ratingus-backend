package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.api.dtos.profile.SchoolDto;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
    School findSchoolById(int i);
}
