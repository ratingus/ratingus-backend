package ru.dnlkk.ratingusbackend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.MergeSubjectWithTeacherRequestDTO;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.ScheduleActionDTO;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.ScheduleChangeDTO;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.ScheduleDTO;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;

@Tag(name = "Контроллер расписания", description = "Просмотр расписания")
@RequestMapping("/schedule")
public interface ScheduleApi {
    @Operation(
            summary = "Объединить предмет с учителем"
    )
    @PostMapping("/merge")
    ResponseEntity mergeSubjectWithTeacher(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody MergeSubjectWithTeacherRequestDTO request);

    @Operation(
            summary = "Добавить предмет в расписание"
    )
    @PostMapping("/{classId}")
    ResponseEntity addSubjectInSchedule(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody ScheduleActionDTO request, @PathVariable(name = "classId") int classId);

    @Operation(
            summary = "Изменить порядок расписания"
    )
    @PatchMapping("/{classId}")
    ResponseEntity changeSubjectInSchedule(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody ScheduleChangeDTO request, @PathVariable(name = "classId") int classId);

    @Operation(
            summary = "Удалить предмет из расписания"
    )
    @DeleteMapping("/{classId}")
    ResponseEntity removeSubjectInSchedule(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody ScheduleActionDTO request, @PathVariable(name = "classId") int classId);

    @Operation(
            summary = "Получение расписания",
            description = "Возвращает расписание по заданному в query-параметре названию класса"
    )
    @GetMapping("/{classId}")
    ResponseEntity<ScheduleDTO> getSchedule(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable(name = "classId") int classId, @RequestParam(name  =  "isAllDay", required = false) boolean isAllDay);

    @Operation(
            summary = "Получение предметов с учителями по классу"
    )
    @GetMapping("/teacher-subjects/{classId}")
    ResponseEntity<ScheduleDTO> getTeachersWithSubject(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable(name = "classId") int classId);
}
