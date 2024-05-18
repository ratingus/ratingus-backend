package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AdminPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPanelController extends ExceptionHandlerController implements AdminPanelApi {
    private final AdminPanelService adminPanelService;

    @Override
    public ResponseEntity getAllUserRolesForSchool() {
        try {
            List<UserRoleDto> allUsersRolesForSchool = adminPanelService.getAllUsersRolesForSchool(2);
            return ResponseEntity.ok(allUsersRolesForSchool);
        } catch (RuntimeException e) { //todo: отдельный контроллер для обработки ошибок ExceptionHandler

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<UserCodeWithClassDto>> getAllUserCodesForSchool() {
        return ResponseEntity.ok(adminPanelService.getAllUsersCodesForSchool(2));
    }


    @Override
    public ResponseEntity<UserCodeWithClassDto> createUserCode(UserCodeWithClassDto userCodeWithClassDto) {
        return ResponseEntity.ok(adminPanelService.createUserCode(userCodeWithClassDto, 2, 2));
    }

    @Override
    public ResponseEntity<UserCodeWithClassDto> updateUserCode(int id, UserCodeWithClassDto userCodeWithClassDto) {
        return ResponseEntity.ok(adminPanelService.updateUserCode(id, userCodeWithClassDto, 2));
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

    @Override
    public ResponseEntity<SubjectDto> createSubject(SubjectDto subjectDto) {
        return ResponseEntity.ok(adminPanelService.createSubject(subjectDto, 2));
    }
}
