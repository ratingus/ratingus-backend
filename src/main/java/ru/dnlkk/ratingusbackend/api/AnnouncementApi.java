package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.model.AnnouncementDto;

import java.util.List;

@Tag(name = "Контроллер объявлений", description = "Просмотр, создание и удаление объявлений")
@RequestMapping("/announcements")
public interface AnnouncementApi {
//todo подключить библиотеки для сваггера
    @Operation(
            summary = "",
            description = ""
    )
    @GetMapping
    ResponseEntity<List<AnnouncementDto>> getAllAnnouncements();

//    @GetMapping("/{announcementId}") //todo: такого вроде нет функционала
//    ResponseEntity<AnnouncementDto> getAnnouncement(@PathVariable("announcementId") int announcementId);

    @Operation(
            summary = "",
            description = ""
    )
    @PostMapping()
    ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto announcementDto);

    @Operation(
            summary = "",
            description = ""
    )
    @DeleteMapping("/{announcementId}")
    ResponseEntity<Void> deleteAnnouncement(@PathVariable("announcementId") int announcementId);
}
