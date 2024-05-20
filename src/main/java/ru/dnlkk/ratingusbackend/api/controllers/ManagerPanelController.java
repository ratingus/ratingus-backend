package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.ManagerPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.ManagerPanelService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ManagerPanelController implements ManagerPanelApi {
    private final ManagerPanelService managerPanelService;
    @Override
    public ResponseEntity<List<ApplicationDto>> getAllApplications(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(managerPanelService.getAllApplications());
    }

    @Override
    public ResponseEntity<ApplicationDto> createApplication(UserDetailsImpl userDetails, ApplicationDto applicationDto) {
        return ResponseEntity.ok(managerPanelService.createApplication(applicationDto, userDetails.getUser())); //todo
    }

    @Override
    public ResponseEntity<Void> deleteApplication(UserDetailsImpl userDetails, int id) {
        managerPanelService.deleteApplication(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SchoolWasCreatedDto> createSchool(UserDetailsImpl userDetails, int applicationId) {
        return ResponseEntity.ok(managerPanelService.createSchool(userDetails, applicationId));
    }
}
