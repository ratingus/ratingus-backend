package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.mapper.user_code.UserCodeMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminPanelService {
    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final UserCodeRepository userCodeRepository;
    private final TimetableRepository timetableRepository;
    private final SchoolRepository schoolRepository;

    public List<UserCodeDto> getAllUsersForSchool(Integer schoolId) {
        System.out.println("Пришли в сервис");
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            System.out.println("Пусто");
            return null;
        } else { //todo: затестировать!!!
            System.out.println("Не пусто");
            List<UserCode> userCodes = school.get().getUserCodes();
            System.out.println("Ура");
            return UserCodeMapper.INSTANCE.toUserCodeDtoList(userCodes);
        }
    }

    public List<UserCode> getAllUserCodes() {
        return userCodeRepository.findAll().stream().toList();
    }

    public UserCode createUserCode(UserCode userCode) {
        return userCodeRepository.saveAndFlush(userCode);
    }

    public List<Class> getAllClasses() {
        return classRepository.findAll().stream().toList();
    }

    public Class createClass(Class classEntity) {
        return classRepository.saveAndFlush(classEntity);
    }

    public List<Timetable> getTimetable() {
        return timetableRepository.findAll().stream().toList();
    }

    //todo: ApplicationDto - исправить
    public List<ApplicationDto> getAllApplications() {
        return null;
    }

    public ResponseEntity<ApplicationDto> createApplication(ApplicationDto applicationDto) {
        return null;
    }

    public ResponseEntity<Void> deleteApplication() {
        return null;
    }
}
