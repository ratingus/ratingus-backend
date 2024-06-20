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

    @Override
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(
            UserDetailsImpl userDetails, Integer offset, Integer limit, Integer classId) {
        List<AnnouncementDto> announcementDtoList = announcementService.getAllAnnouncementsPagination(userDetails, offset, limit, classId);
        return ResponseEntity.ok(announcementDtoList);
    }

    @Override
    public ResponseEntity<AnnouncementDto> createAnnouncement(
            UserDetailsImpl userDetails, AnnouncementCreateDto announcementCreateDto) {
        AnnouncementDto announcementDtoFromService = announcementService.createAnnouncement(userDetails, announcementCreateDto);
        return ResponseEntity.ok(announcementDtoFromService);
    }

    @Override
    public ResponseEntity<Void> deleteAnnouncement(UserDetailsImpl userDetails, int id) {
        announcementService.deleteAnnouncementById(userDetails, id);
        return ResponseEntity.ok().build();
    }
}
