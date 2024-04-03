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
            summary = "Получение списка объявлений",
            description = "Возвращает список объявлений учебной организации"
    )
    @GetMapping
    ResponseEntity<List<AnnouncementDto>> getAllAnnouncements();

    @Operation(
            summary = "Создание объявления",
            description = "Создаёт и возвращает объявление"
    )
    @PostMapping()
    ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto announcementDto);

    @Operation(
            summary = "Удаление объявления",
            description = "Удаляет объявление и ничего не возвращает"
    )
    @DeleteMapping("/{announcementId}")
    ResponseEntity<Void> deleteAnnouncement(@PathVariable("announcementId") int announcementId);
}
