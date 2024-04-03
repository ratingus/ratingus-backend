package ru.dnlkk.ratingusbackend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.model.AnnouncementDto;

import java.util.List;

@RequestMapping("/announcements")
public interface AnnouncementApi {
//todo подключить библиотеки для сваггера
    @GetMapping
    ResponseEntity<List<AnnouncementDto>> getAllAnnouncements();

//    @GetMapping("/{announcementId}") //todo: такого вроде нет функционала
//    ResponseEntity<AnnouncementDto> getAnnouncement(@PathVariable("announcementId") int announcementId);

    @PostMapping()
    ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto announcementDto);

    @DeleteMapping("/{announcementId}")
    ResponseEntity<Void> deleteAnnouncement(@PathVariable("announcementId") int announcementId);
}
