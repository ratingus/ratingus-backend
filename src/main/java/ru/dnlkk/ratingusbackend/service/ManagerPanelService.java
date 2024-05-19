package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationIdDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
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

    public SchoolWasCreatedDto createSchool(ApplicationIdDto applicationIdDto) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationIdDto.getId());
        if (applicationOptional.isEmpty()) {
            return null; //todo ошибка 404?
        }
        School school = new School();
        Application application = applicationOptional.get();
        school.setName(application.getOrganisationName()); //todo: вынести в маппер (в SchoolMapper)
        school.setEmail(application.getOrganisationMail());
        school.setAddress(application.getOrganisationAddress());
        school.setPhone(application.getOrganisationPhone());
        School schoolAfterSaving = schoolRepository.saveAndFlush(school);

        deleteApplication(applicationIdDto.getId());

        return SchoolMapper.INSTANCE.toSchoolWasCreatedDto(schoolAfterSaving);
    }
}
