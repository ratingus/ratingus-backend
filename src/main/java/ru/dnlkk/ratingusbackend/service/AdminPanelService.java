package ru.dnlkk.ratingusbackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.model.Timetable;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserCode;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.repository.ClassRepository;
import ru.dnlkk.ratingusbackend.repository.TimetableRepository;
import ru.dnlkk.ratingusbackend.repository.UserCodeRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;

import java.util.List;

@Service
public class AdminPanelService {
    private ClassRepository classRepository;
    private UserRepository userRepository;
    private UserCodeRepository userCodeRepository;
    private TimetableRepository timetableRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().toList();
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
