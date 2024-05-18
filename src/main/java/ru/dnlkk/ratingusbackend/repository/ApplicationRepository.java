package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dnlkk.ratingusbackend.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}