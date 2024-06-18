package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.ScheduleApi;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.MergeSubjectWithTeacherRequestDTO;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.ScheduleActionDTO;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.ScheduleChangeDTO;
import ru.dnlkk.ratingusbackend.api.dtos.schedule.ScheduleDTO;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.ScheduleService;

@RestController
@RequiredArgsConstructor
public class ScheduleController implements ScheduleApi {
    private final ScheduleService scheduleService;

    @Override
    public ResponseEntity mergeSubjectWithTeacher(UserDetailsImpl user, MergeSubjectWithTeacherRequestDTO request) {
        scheduleService.mergeSubjectWithTeacher(user.getUserRole(), request);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity addSubjectInSchedule(UserDetailsImpl user, ScheduleActionDTO request, int classId) {
        scheduleService.addSubjectInSchedule(user.getUserRole(), request, classId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity changeSubjectInSchedule(UserDetailsImpl user, ScheduleChangeDTO request, int classId) {
        scheduleService.changeSubjectInSchedule(user.getUserRole(), request, classId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity removeSubjectInSchedule(UserDetailsImpl user, ScheduleActionDTO request, int classId) {
        scheduleService.removeSubjectFromSchedule(user.getUserRole(), request, classId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ScheduleDTO> getSchedule(UserDetailsImpl user, int classId, boolean isAllDay) {
        return ResponseEntity.ok(scheduleService.getSchedule(user, classId, isAllDay));
    }

    @Override
    public ResponseEntity getTeachersWithSubject(UserDetailsImpl user, int classId) {
        return ResponseEntity.ok(scheduleService.getTeachersWithSubject(classId));
    }
}
