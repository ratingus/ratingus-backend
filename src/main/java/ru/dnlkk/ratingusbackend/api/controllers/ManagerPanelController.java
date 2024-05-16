package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.ManagerPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.ApplicationDto;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ManagerPanelController implements ManagerPanelApi {
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
