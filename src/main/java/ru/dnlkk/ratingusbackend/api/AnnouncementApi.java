package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.AnnouncementDto;

import java.util.List;

@Tag(name = "Контроллер объявлений", description = "Просмотр, создание и удаление объявлений")
@RequestMapping("/announcements")
public interface AnnouncementApi {
    String defaultOffset = "0"; //todo: можно ли задать в .yaml-файле значение?
    String defaultLimit = "25";

    @Operation(
            summary = "Получение объявления по id",
            description = "Возвращает объявление по указанному id"
    )
    @GetMapping("/{id}")
    ResponseEntity<AnnouncementDto> getAnnouncementById(
            @Schema(description = "Id запрашиваемого объявления")
            @PathVariable int id
    );

    @Operation(
            summary = "Получение списка объявлений",
            description = "Возвращает список объявлений учебной организации (с пагинацией через query-параметры)"
    )
    @GetMapping
    ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(
            @Schema(description = "Номер страницы для пагинации", minimum = "0", defaultValue = defaultOffset)
            @RequestParam(name = "offset", defaultValue = defaultOffset)
            @Min(0)
            Integer offset,

            @Schema(description = "Количество объявлений на странице для пагинации", minimum = "1", maximum = "50", defaultValue = defaultLimit)
            @RequestParam(name = "limit", defaultValue = defaultLimit)
            @Min(1) @Max(50)
            Integer limit,

            @Schema(description = "Id класса", minimum = "0")
            @RequestParam(name = "classId", required = false)
            @Min(0)
            Integer classId
    );


    @Operation(
            summary = "Создание объявления",
            description = "Создаёт и возвращает объявление"
    )
    @PostMapping()
    ResponseEntity<AnnouncementDto> createAnnouncement(
            @Schema(description = "DTO создаваемого объявления")
            @RequestBody AnnouncementDto announcementDto
    );


    @Operation(
            summary = "Удаление объявления",
            description = "Удаляет объявление и ничего не возвращает"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAnnouncement(
            @Schema(description = "Id удаляемого объявления")
            @PathVariable("id") int id
    );
}
