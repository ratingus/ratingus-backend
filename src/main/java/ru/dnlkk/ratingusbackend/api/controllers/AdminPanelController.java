package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AdminPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPanelController implements AdminPanelApi {
    private final AdminPanelService adminPanelService;

    @Override
    public ResponseEntity<List<UserRoleDto>> getAllUserRolesForSchool() {
        return ResponseEntity.ok(adminPanelService.getAllUsersRolesForSchool(2));
    }

    @Override
    public ResponseEntity<List<UserCodeDto>> getAllUserCodesFroSchool() {
        return ResponseEntity.ok(adminPanelService.getAllUsersCodesForSchool(2));
    }

    @Override
    public ResponseEntity<UserCodeCreateDto> createUserCode(UserCodeCreateDto userCodeCreateDto) {
        return ResponseEntity.ok(adminPanelService.createUserCode(userCodeCreateDto));
    }

    @Override
    public ResponseEntity<List<ClassDto>> getAllClasses() {
        return ResponseEntity.ok(adminPanelService.getAllClassesForSchool(2));
    }

    @Override
    public ResponseEntity<ClassDto> createClass(ClassDto classDto) {
        return ResponseEntity.ok(adminPanelService.createClass(classDto, 2));
    }

    @Override
    public ResponseEntity<Void> deleteClass(Integer id) {
        adminPanelService.deleteClass(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<TimetableDto>> getTimetable() {
        return ResponseEntity.ok(adminPanelService.getTimetable(2));
    }
}
