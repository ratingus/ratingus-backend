package ru.dnlkk.ratingusbackend.api.controllers;

import org.springframework.http.ResponseEntity;
import ru.dnlkk.ratingusbackend.api.AdminPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;

import java.util.List;

public class AdminPanelController implements AdminPanelApi {
    private AdminPanelService adminPanelService;

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return null;
    }

    @Override
    public ResponseEntity<List<UserCodeDto>> getAllUserCodes() {
        return null;
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
