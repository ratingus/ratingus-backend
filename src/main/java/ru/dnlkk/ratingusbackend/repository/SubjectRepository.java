package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findSubjectByName(String name);
}
