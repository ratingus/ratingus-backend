package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
}
