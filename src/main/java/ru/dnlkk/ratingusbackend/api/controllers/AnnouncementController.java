package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AnnouncementApi;
import ru.dnlkk.ratingusbackend.api.dtos.AnnouncementDto;
import ru.dnlkk.ratingusbackend.exceptions.ForbiddenException;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.mapper.announcement.AnnouncementMapper;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.service.AnnouncementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnnouncementController extends ExceptionHandlerController implements AnnouncementApi {
    private final AnnouncementService announcementService;

    private int testSchoolId = 2;
    private int testUserId = 2;

    @Override
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(Integer offset, Integer limit, Integer classId) {
        List<AnnouncementDto> announcementDtoList = announcementService.getAllAnnouncementsPagination(offset, limit, classId, testSchoolId);
        return ResponseEntity.ok(announcementDtoList);
    }

    @Override
    public ResponseEntity<AnnouncementDto> getAnnouncementById(int id) {
        AnnouncementDto announcementDto = announcementService.getAnnouncementById(id, testSchoolId);
        return ResponseEntity.ok(announcementDto);
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
