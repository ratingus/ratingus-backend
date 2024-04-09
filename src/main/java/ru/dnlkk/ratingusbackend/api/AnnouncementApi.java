package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.AnnouncementDto;

import java.util.List;

@Tag(name = "Контроллер объявлений", description = "Просмотр, создание и удаление объявлений")
@RequestMapping("/announcements")
public interface AnnouncementApi {
    @Operation(
            summary = "Получение списка объявлений",
            description = "Возвращает список объявлений учебной организации (с пагинацией через query-параметры)"
    )
    @GetMapping
    ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "25") Integer limit
    );


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
