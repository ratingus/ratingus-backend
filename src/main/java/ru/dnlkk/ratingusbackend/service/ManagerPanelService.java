package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.exceptions.ForbiddenException;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.mapper.ApplicationMapper;
import ru.dnlkk.ratingusbackend.mapper.SchoolMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.repository.ApplicationRepository;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;
import ru.dnlkk.ratingusbackend.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerPanelService {
    private final ApplicationRepository applicationRepository;
    private final UserRoleRepository userRoleRepository;

    private void checkIsUserManager(UserDetailsImpl userDetails) {
        if (!userDetails.getUser().getIsAdmin()) {
            throw new ForbiddenException("Доступ запрещён");
        }
    }

    private final SchoolRepository schoolRepository;
    public List<ApplicationDto> getAllApplications(UserDetailsImpl userDetails) {
        checkIsUserManager(userDetails);
        List<Application> applicationList = applicationRepository.findAll();
        return ApplicationMapper.INSTANCE.toDtoList(applicationList);
    }

    public ApplicationDto createApplication(UserDetailsImpl userDetails, ApplicationDto applicationDto, User user) {
        checkIsUserManager(userDetails);
        Application application = ApplicationMapper.INSTANCE.toEntity(applicationDto);
        application.setCreator(user);
        Application applicationAfterSaving = applicationRepository.saveAndFlush(application);
        return ApplicationMapper.INSTANCE.toDto(applicationAfterSaving);
    }

    public void deleteApplication(UserDetailsImpl userDetails, int id) {
        checkIsUserManager(userDetails);
        applicationRepository.deleteById(id);
    }

    public SchoolWasCreatedDto createSchool(UserDetailsImpl userDetails, int applicationId) {
        checkIsUserManager(userDetails);
        System.out.println("СОЗДАЁМ школу!");
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

        deleteApplication(userDetails, applicationId);

        User manager = userDetails.getUser();

        UserRole localAdmin = new UserRole();
        localAdmin.setSchool(schoolAfterSaving);
        localAdmin.setRole(Role.LOCAL_ADMIN);
        localAdmin.setUser(manager);
        localAdmin.setName(manager.getName());
        localAdmin.setSurname(manager.getSurname());
        localAdmin.setPatronymic(manager.getPatronymic());
        userRoleRepository.saveAndFlush(localAdmin);

        return SchoolMapper.INSTANCE.toSchoolWasCreatedDto(schoolAfterSaving);
    }
}
