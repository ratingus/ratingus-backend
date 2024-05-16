package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationIdDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;

import java.util.List;

@RequestMapping("/admin-panel-manager")
@Tag(name = "Контроллер админ-панели для менеджера", description = "Управление заявками на создание учебной организации")
public interface ManagerPanelApi {
    @Operation(
            summary = "Получение всех заявок",
            description = "Возвращает список всех заявок на создание школы"
    )
    @GetMapping("/application")
    ResponseEntity<List<ApplicationDto>> getAllApplications();

    //todo: мб этот метод не тут должен быть
    @Operation(
            summary = "Создание заявки",
            description = "Создаёт заявку на создание школы и возвращает её"
    )
    @PostMapping("/application")
    ResponseEntity<ApplicationDto> createApplication(@RequestBody ApplicationDto applicationDto);


    @Operation(
            summary = "Удаление заявки",
            description = "Удаляет заявку на создание школы и ничего не возвращает"
    )
    @DeleteMapping("/application/{id}")
    ResponseEntity<Void> deleteApplication(
            @Schema(description = "Id удаляемой заявки")
            @PathVariable("id") int id
    );

    @Operation(
            summary = "Создание новой школы (одобрение заявки)",
            description = "Создаёт школу"
    )
    @PostMapping("/application-approve")
    ResponseEntity<SchoolWasCreatedDto> createSchool(@RequestBody ApplicationIdDto applicationIdDto);
}
