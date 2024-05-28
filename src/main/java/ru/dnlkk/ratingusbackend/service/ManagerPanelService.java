package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationStatusType;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.exceptions.ForbiddenException;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.mapper.ApplicationMapper;
import ru.dnlkk.ratingusbackend.mapper.SchoolMapper;
import ru.dnlkk.ratingusbackend.mapper.user_code.UserCodeMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.repository.*;
import ru.dnlkk.ratingusbackend.service.util.RandomSequenceGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerPanelService {
    private final ApplicationRepository applicationRepository;
    private final UserRoleRepository userRoleRepository;

    private final String[] endTimes = {
            "2024-05-21 08:40:00.000000",
            "2024-05-21 09:30:00.000000",
            "2024-05-21 10:25:00.000000",
            "2024-05-21 11:25:00.000000",
            "2024-05-21 12:20:00.000000",
            "2024-05-21 13:15:00.000000",
            "2024-05-21 14:05:00.000000",
            "2024-05-21 14:55:00.000000"
    };

    private final String[] startTimes = {
            "2024-05-21 08:00:00.000000",
            "2024-05-21 08:50:00.000000",
            "2024-05-21 09:45:00.000000",
            "2024-05-21 10:45:00.000000",
            "2024-05-21 11:40:00.000000",
            "2024-05-21 12:35:00.000000",
            "2024-05-21 13:25:00.000000",
            "2024-05-21 14:15:00.000000"
    };
    private final TimetableRepository timetableRepository;
    private final UserCodeRepository userCodeRepository;

    private void checkIsUserManager(UserDetailsImpl userDetails) {
        if (Boolean.FALSE.equals(userDetails.getUser().getIsAdmin())) {
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

    public School createSchool(UserDetailsImpl userDetails, UserCodeWithClassDto userCodeWithClassDto, int applicationId) {
        checkIsUserManager(userDetails);
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new NotFoundException("Не существует заявки с id=" + applicationId);
        }

        School school = new School();
        Application application = applicationOptional.get();
        school.setName(application.getName());
        school.setEmail(application.getEmail());
        school.setAddress(application.getAddress());
        school.setPhone(application.getPhone());
        School schoolAfterSaving = schoolRepository.saveAndFlush(school);

        List<Timetable> timetables = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Timetable timetable = new Timetable();
            timetable.setSchool(schoolAfterSaving);
            timetable.setLessonNumber(i);
            timetable.setStartTime(Timestamp.valueOf(startTimes[i - 1]));
            timetable.setEndTime(Timestamp.valueOf(endTimes[i - 1]));
            timetables.add(timetable);
        }
        timetableRepository.saveAll(timetables);
        school.setTimetables(timetables);

        deleteApplication(userDetails, applicationId);

        User manager = userDetails.getUser();

        UserRole localAdmin = new UserRole();
        localAdmin.setSchool(schoolAfterSaving);
        localAdmin.setRole(Role.LOCAL_ADMIN);
        localAdmin.setUser(manager);
        localAdmin.setName(manager.getName());
        localAdmin.setSurname(manager.getSurname());
        localAdmin.setPatronymic(manager.getPatronymic());
        localAdmin.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRoleRepository.saveAndFlush(localAdmin);

        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeWithClassDto);

        userCode.setCreator(userDetails.getUser());
        userCode.setSchool(schoolAfterSaving);

        var code = RandomSequenceGenerator.generateRandomSequence() + school.getId();
        userCode.setCode(code);
        var finalUserCode = userCodeRepository.saveAndFlush(userCode);

        application.setCode(finalUserCode);
        application.setStatus(ApplicationStatusType.APPROVED);
        applicationRepository.saveAndFlush(application);

        return school;
    }

    public void rejectApplication(UserDetailsImpl userDetails, int id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(ApplicationStatusType.REJECTED);
        applicationRepository.saveAndFlush(application);
    }

    public List<SchoolWasCreatedDto> getAllSchools(UserDetailsImpl userDetails){
        checkIsUserManager(userDetails);
        List<School> schools = schoolRepository.findAll();
        return SchoolMapper.INSTANCE.toSchoolDtoList(schools);
    }
}
