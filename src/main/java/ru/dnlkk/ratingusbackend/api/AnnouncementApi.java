package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.model.AnnouncementDto;

import java.util.List;

@Tag(name = "Контроллер объявлений", description = "Просмотр, создание и удаление объявлений")
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