package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationResponseDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserForAdminPanelDto;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserForManagerDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.EditUserRoleDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;

import java.util.List;

@RequestMapping("/admin-panel-manager")
@Tag(name = "Контроллер админ-панели для менеджера", description = "Управление заявками на создание учебной организации")
public interface ManagerPanelApi {
    @Operation(
            summary = "Получение всех заявок",
            description = "Возвращает список всех заявок на создание школы"
    )
    @GetMapping("/application")
    ResponseEntity<List<ApplicationResponseDto>> getAllApplications(@AuthenticationPrincipal UserDetailsImpl userDetails);

    @Operation(
            summary = "Создание заявки",
            description = "Создаёт заявку на создание школы и возвращает её"
    )
    @PostMapping("/application")
    ResponseEntity<ApplicationDto> createApplication(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "DTO создаваемой заявки")
            @RequestBody ApplicationDto applicationDto);


    @Operation(
            summary = "Удаление заявки",
            description = "Удаляет заявку на создание школы и ничего не возвращает"
    )
    @DeleteMapping("/application/{id}")
    ResponseEntity<Void> deleteApplication(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "Id удаляемой заявки")
            @PathVariable("id") int id
    );

    @Operation(
            summary = "Отклонение заявки",
            description = "Отклоняет заявку на создание школы и ничего не возвращает"
    )
    @PostMapping("/application-reject/{id}")
    ResponseEntity<Void> rejectApplication(@AuthenticationPrincipal UserDetailsImpl userDetails, @Schema(description = "Id отклоняемой заявки")
    @PathVariable("id") int id);

    @Operation(
            summary = "Создание новой школы (одобрение заявки)",
            description = "Создаёт школу и возвращает её"
    )
    @PostMapping("/application-approve/{id}")
    ResponseEntity<SchoolWasCreatedDto> createSchool(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
      
            @Schema(description = "Id одобренной заявки")
            @PathVariable(name = "id") int applicationId,
            @RequestBody UserCodeWithClassDto userCodeWithClassDto
    );
  
    @Operation(
            summary = "Получение всех пользователей",
            description = "Возвращает список всех польхователей, которые есть в базе данных"
    )
    @GetMapping("/users")
    ResponseEntity<List<UserForManagerDto>> getAllUsers(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
  
    @Operation(
            summary = "Получение всех школ",
            description = "Возвращает список всех школ, которые есть в базе данных"
    )
    @GetMapping("/school")
    ResponseEntity<List<SchoolWasCreatedDto>> getAllSchools(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    );
}
