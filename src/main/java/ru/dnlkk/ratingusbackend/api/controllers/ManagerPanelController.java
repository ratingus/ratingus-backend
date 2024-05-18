package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.ManagerPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationIdDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.service.ManagerPanelService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ManagerPanelController implements ManagerPanelApi {
    private final ManagerPanelService managerPanelService;
    @Override
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {
        return ResponseEntity.ok(managerPanelService.getAllApplications());
    }

    @Override
    public ResponseEntity<ApplicationDto> createApplication(ApplicationDto applicationDto) {
        return ResponseEntity.ok(managerPanelService.createApplication(applicationDto, 2)); //todo
    }

    @Override
    public ResponseEntity<Void> deleteApplication(int id) {
        managerPanelService.deleteApplication(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SchoolWasCreatedDto> createSchool(ApplicationIdDto applicationIdDto) {
        return ResponseEntity.ok(managerPanelService.createSchool(applicationIdDto));
    }
}
