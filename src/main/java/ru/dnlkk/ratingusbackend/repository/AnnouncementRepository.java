package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
}
