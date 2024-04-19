package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AnnouncementApi;
import ru.dnlkk.ratingusbackend.api.model.AnnouncementDto;
import ru.dnlkk.ratingusbackend.mapper.AnnouncementMapper;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.service.AnnouncementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnnouncementController implements AnnouncementApi {
    private final AnnouncementService announcementService;

    @Override
    public ResponseEntity<AnnouncementDto> getAnnouncementById(int id) {
        AnnouncementDto announcementDto = AnnouncementMapper.INSTANCE.toDto(announcementService.getAnnouncementById(id));
        return ResponseEntity.ok(announcementDto);
    }

    @Override
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public ResponseEntity<AnnouncementDto> createAnnouncement(AnnouncementDto announcementDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAnnouncement(int announcementId) {
        return null;
    }
}
