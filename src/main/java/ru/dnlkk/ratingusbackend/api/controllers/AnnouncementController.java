package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AnnouncementApi;
import ru.dnlkk.ratingusbackend.api.dtos.announcement.AnnouncementCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.announcement.AnnouncementDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.AnnouncementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnnouncementController extends ExceptionHandlerController implements AnnouncementApi {
    private final AnnouncementService announcementService;

    private final int schoolIdTest = 2;
    private final int userIdTest = 2;

    @Override
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(Integer offset, Integer limit, Integer classId) {
        List<AnnouncementDto> announcementDtoList = announcementService.getAllAnnouncementsPagination(offset, limit, classId, schoolIdTest);
        return ResponseEntity.ok(announcementDtoList);
    }

    @Override
    public ResponseEntity<AnnouncementDto> getAnnouncementById(int id) {
        AnnouncementDto announcementDto = announcementService.getAnnouncementById(id, schoolIdTest);
        return ResponseEntity.ok(announcementDto);
    }

    @Override
    public ResponseEntity<AnnouncementDto> createAnnouncement(AnnouncementCreateDto announcementCreateDto, UserDetailsImpl creator) {
        AnnouncementDto announcementDtoFromService = announcementService.createAnnouncement(announcementCreateDto, creator);
        return ResponseEntity.ok(announcementDtoFromService);
    }

    @Override
    public ResponseEntity<Void> deleteAnnouncement(int id) {
        announcementService.deleteAnnouncementById(id);
        return ResponseEntity.ok().build();
    }
}
