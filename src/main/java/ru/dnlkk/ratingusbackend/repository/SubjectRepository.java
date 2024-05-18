package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnlkk.ratingusbackend.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}