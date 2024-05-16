package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.mapper.ApplicationMapper;
import ru.dnlkk.ratingusbackend.model.Application;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.repository.ApplicationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerPanelService {
    private final ApplicationRepository applicationRepository;
    public List<ApplicationDto> getAllApplications() {
        List<Application> applicationList = applicationRepository.findAll();
        return ApplicationMapper.INSTANCE.toDtoList(applicationList);
    }

    public ApplicationDto createApplication(ApplicationDto applicationDto, int creatorId) {
        Application application = ApplicationMapper.INSTANCE.toEntity(applicationDto);
        User creator = new User();
        creator.setId(creatorId);
        application.setCreator(creator);
        Application applicationAfterSaving = applicationRepository.saveAndFlush(application);
        return ApplicationMapper.INSTANCE.toDto(applicationAfterSaving);
    }

    public void deleteApplication(int id) {
        applicationRepository.deleteById(id);
    }
}
