package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.ManagerPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;
import ru.dnlkk.ratingusbackend.service.ManagerPanelService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ManagerPanelController implements ManagerPanelApi {
    private final ManagerPanelService managerPanelService;
    private final AdminPanelService adminPanelService;
    @Override
    public ResponseEntity<List<ApplicationDto>> getAllApplications(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(managerPanelService.getAllApplications(userDetails));
    }

    @Override
    public ResponseEntity<ApplicationDto> createApplication(UserDetailsImpl userDetails, ApplicationDto applicationDto) {
        return ResponseEntity.ok(managerPanelService.createApplication(userDetails, applicationDto, userDetails.getUser())); //todo
    }

    @Override
    public ResponseEntity<Void> deleteApplication(UserDetailsImpl userDetails, int id) {
        managerPanelService.deleteApplication(userDetails, id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> rejectApplication(UserDetailsImpl userDetails, int id) {
        managerPanelService.rejectApplication(userDetails, id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SchoolWasCreatedDto> createSchool(UserDetailsImpl userDetails, int applicationId, UserCodeWithClassDto userCodeWithClassDto) {
        userCodeWithClassDto.setRole(Role.LOCAL_ADMIN);
        managerPanelService.createSchool(userDetails, userCodeWithClassDto, applicationId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<SchoolWasCreatedDto>> getAllSchools(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(managerPanelService.getAllSchools(userDetails));
    }
}
