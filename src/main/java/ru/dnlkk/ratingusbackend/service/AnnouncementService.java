package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.repository.AnnouncementRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncementsPagination(int offset, int limit, Integer classId) {
        if (classId != null) {
            return announcementRepository.findByClasses_Id(classId, PageRequest.of(offset, limit));
        }
        return announcementRepository.findAll(PageRequest.of(offset, limit)).stream().toList();
    }

    public Announcement getAnnouncementById(int id) { //если возвращается null...
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        return optionalAnnouncement.orElse(null);
    }

    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.saveAndFlush(announcement);
    }

    public void deleteAnnouncementById(int id) {
        announcementRepository.deleteById(id);
    }
}
