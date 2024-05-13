package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AdminPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPanelController implements AdminPanelApi {
    private final AdminPanelService adminPanelService;

    @Override
    public ResponseEntity<List<UserCodeDto>> getAllUserCodesForSchool() {
        return ResponseEntity.ok(adminPanelService.getAllUsersCodesForSchool(2)); //todo: получаем schoolId
    }

    @Override
    public ResponseEntity<UserCodeDto> createUserCode(UserCodeDto userCodeDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<ClassDto>> getAllClasses() {
        return null;
    }

    @Override
    public ResponseEntity<ClassDto> createClass(ClassDto classDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<TimetableDto>> getTimetable() {
        return null;
    }

    @Override
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {
        return null;
    }

    @Override
    public ResponseEntity<ApplicationDto> createApplication(ApplicationDto applicationDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteApplication() {
        return null;
    }
}
