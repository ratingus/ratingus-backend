package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.ApplicationDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPanelManagerService {
    public List<ApplicationDto> getAllApplications() {
        return null;
    }

    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        return null;
    }

    public void deleteApplication() {
    }
}
