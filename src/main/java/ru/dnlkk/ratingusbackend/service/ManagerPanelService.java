package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.mapper.ApplicationMapper;
import ru.dnlkk.ratingusbackend.mapper.SchoolMapper;
import ru.dnlkk.ratingusbackend.model.Application;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.repository.ApplicationRepository;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerPanelService {
    private final ApplicationRepository applicationRepository;
    private final SchoolRepository schoolRepository;
    public List<ApplicationDto> getAllApplications() {
        List<Application> applicationList = applicationRepository.findAll();
        return ApplicationMapper.INSTANCE.toDtoList(applicationList);
    }

    public ApplicationDto createApplication(ApplicationDto applicationDto, User user) {
        Application application = ApplicationMapper.INSTANCE.toEntity(applicationDto);
        application.setCreator(user);
        Application applicationAfterSaving = applicationRepository.saveAndFlush(application);
        return ApplicationMapper.INSTANCE.toDto(applicationAfterSaving);
    }

    public void deleteApplication(int id) {
        applicationRepository.deleteById(id);
    }

    public SchoolWasCreatedDto createSchool(int applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new NotFoundException("Не существует заявки с id=" + applicationId);
        }

        School school = new School();
        Application application = applicationOptional.get();
        school.setName(application.getOrganisationName());
        school.setEmail(application.getOrganisationMail());
        school.setAddress(application.getOrganisationAddress());
        school.setPhone(application.getOrganisationPhone());
        School schoolAfterSaving = schoolRepository.saveAndFlush(school);

        deleteApplication(applicationId);

        return SchoolMapper.INSTANCE.toSchoolWasCreatedDto(schoolAfterSaving);
    }
}
