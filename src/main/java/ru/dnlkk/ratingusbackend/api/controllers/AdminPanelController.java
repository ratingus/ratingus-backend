package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AdminPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPanelController extends ExceptionHandlerController implements AdminPanelApi {
    private final AdminPanelService adminPanelService;

    @Override
    public ResponseEntity<List<UserRoleDto>> getAllUserRolesForSchool() {
        List<UserRoleDto> allUsersRolesForSchool = adminPanelService.getAllUsersRolesForSchool(1);
        return ResponseEntity.ok(allUsersRolesForSchool);
    }

    @Override
    public ResponseEntity<List<UserCodeWithClassDto>> getAllUserCodesForSchool() {
        return ResponseEntity.ok(adminPanelService.getAllUsersCodesForSchool(1));
    }


    @Override
    public ResponseEntity<UserCodeWithClassDto> createUserCode(UserCodeWithClassDto userCodeWithClassDto, UserDetailsImpl user) {
        return ResponseEntity.ok(adminPanelService.createUserCode(userCodeWithClassDto, user, 1));
    }

    @Override
    public ResponseEntity<UserCodeWithClassDto> updateUserCode(int id, UserCodeWithClassDto userCodeWithClassDto) {
        return ResponseEntity.ok(adminPanelService.updateUserCode(id, userCodeWithClassDto, 1));
    }

    @Override
    public ResponseEntity<List<ClassDto>> getAllClasses() {
        return ResponseEntity.ok(adminPanelService.getAllClassesForSchool(1));
    }

    @Override
    public ResponseEntity<ClassDto> createClass(ClassDto classDto) {
        return ResponseEntity.ok(adminPanelService.createClass(classDto, 1));
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
    public ResponseEntity<List<TeacherSubjectDto>> getAllSubjects() {
        return ResponseEntity.ok(adminPanelService.getAllSubjects(2));
    }

    @Override
    public ResponseEntity<SubjectDto> createSubject(SubjectCreateDto subjectDto) {
        return ResponseEntity.ok(adminPanelService.createSubject(subjectDto, 2));
    }

    @Override
    public ResponseEntity<TeacherSubjectDto> setTeacherToSubject(TeacherSubjectCreateDto teacherSubjectCreateDto) {
        return ResponseEntity.ok(adminPanelService.setTeacherToSubject(teacherSubjectCreateDto, 2));
    }

    @Override
    public ResponseEntity<TeacherSubjectDto> deleteTeacherToSubject(int teacherSubjectId) {
        adminPanelService.deleteTeacherToSubject(teacherSubjectId, 2);
        return ResponseEntity.ok().build();
    }
}
