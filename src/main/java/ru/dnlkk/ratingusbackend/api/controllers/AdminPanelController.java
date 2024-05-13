package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AdminPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.mapper.UserMapper;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPanelController implements AdminPanelApi {
    private final AdminPanelService adminPanelService;

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = adminPanelService.getAllUsers();
        List<UserDto> userDtos = UserMapper.INSTANCE.toDtoList(users);
        return ResponseEntity.ok(userDtos);
    }

    @Override
    public ResponseEntity<List<UserCodeDto>> getAllUserCodes() {
        return null;
    }

    @Override
    public ResponseEntity<UserCodeDto> createUserCode(UserCodeDto userCodeDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<ClassDto>> getAllClasses() {
        return null;
    }

    @Override
    public ResponseEntity<ClassDto> createClass(ClassDto classDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<TimetableDto>> getTimetable() {
        return null;
    }

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
