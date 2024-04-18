package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.repository.AnnouncementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncementsPagination(int offset, int limit) {
        return announcementRepository.findAll(PageRequest.of(offset, limit)).stream().toList();
    }

    public Announcement getAnnouncementById(int id) {
        return announcementRepository.findById(id).orElseThrow(() -> new NotFoundException("Announcement with the specified id was not found"));
    }

    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.saveAndFlush(announcement);
    }

    public void deleteAnnouncementById(int id) {
        announcementRepository.deleteById(id);
    }
}
