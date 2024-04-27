package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AnnouncementApi;
import ru.dnlkk.ratingusbackend.api.dtos.AnnouncementDto;
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
        Announcement announcementFromService = announcementService.getAnnouncementById(id);
        if (announcementFromService == null) {
            return ResponseEntity.notFound().build();
        } else {
            AnnouncementDto announcementDto = AnnouncementMapper.INSTANCE.toDto(announcementFromService);
            return ResponseEntity.ok(announcementDto);
        }
    }

    @Override
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(Integer offset, Integer limit, Integer classId) {
        List<Announcement> announcementsFromService = announcementService.getAllAnnouncementsPagination(offset, limit, classId);
        List<AnnouncementDto> announcementDtos = AnnouncementMapper.INSTANCE.toDtoList(announcementsFromService);
        return ResponseEntity.ok(announcementDtos);
    }

    @Override
    public ResponseEntity<AnnouncementDto> createAnnouncement(AnnouncementDto announcementDto) {
        Announcement announcement = AnnouncementMapper.INSTANCE.toModel(announcementDto);
        Announcement announcementFromService = announcementService.createAnnouncement(announcement);
        AnnouncementDto announcementDtoFromService = AnnouncementMapper.INSTANCE.toDto(announcementFromService);
        return ResponseEntity.ok(announcementDtoFromService);
    }

    @Override
    public ResponseEntity<Void> deleteAnnouncement(int id) {
        announcementService.deleteAnnouncementById(id);
        return ResponseEntity.ok().build();
    }
}
