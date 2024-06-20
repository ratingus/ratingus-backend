package ru.dnlkk.ratingusbackend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.api.AdminPanelApi;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolProfileDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectsDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.EditUserRoleDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleSimpleDto;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.service.AdminPanelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPanelController extends ExceptionHandlerController implements AdminPanelApi {

    private final AdminPanelService adminPanelService;

    @Override
    public ResponseEntity<List<UserRoleDto>> getAllUserRolesForSchool(UserDetailsImpl userDetails) {
        List<UserRoleDto> allUsersRolesForSchool = adminPanelService.getAllUsersRolesForSchool(userDetails);
        return ResponseEntity.ok(allUsersRolesForSchool);
    }

    @Override
    public ResponseEntity<List<UserRoleSimpleDto>> getAllTeachersForSchool(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(adminPanelService.getAllTeachersForSchool(userDetails));
    }

    @Override
    public ResponseEntity<List<UserCodeWithClassDto>> getAllUserCodesForSchool(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(adminPanelService.getAllUsersCodesForSchool(userDetails));
    }


    @Override
    public ResponseEntity<UserCodeWithClassDto> createUserCode(
            UserDetailsImpl userDetails, UserCodeWithClassDto userCodeWithClassDto) {
        return ResponseEntity.ok(adminPanelService.createUserCode(userCodeWithClassDto, userDetails, null));
    }

    @Override
    public ResponseEntity<UserCodeWithClassDto> updateUserCode(
            UserDetailsImpl userDetails, int id, UserCodeWithClassDto userCodeWithClassDto) {
        return ResponseEntity.ok(adminPanelService.updateUserCode(id, userCodeWithClassDto, userDetails));
    }

    @Override
    public ResponseEntity<List<ClassDto>> getAllClasses(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(adminPanelService.getAllClassesForSchool(userDetails));
    }

    @Override
    public ResponseEntity<ClassDto> createClass(UserDetailsImpl userDetails, ClassDto classDto) {
        return ResponseEntity.ok(adminPanelService.createClass(classDto, userDetails));
    }

    @Override
    public ResponseEntity<ClassDto> updateClass(int id, UserDetailsImpl userDetails, ClassDto classDto) {
        return ResponseEntity.ok(adminPanelService.updateClass(id, classDto, userDetails));
    }

    @Override
    public ResponseEntity<Void> deleteClass(UserDetailsImpl userDetails, Integer id) {
        adminPanelService.deleteClass(id, userDetails);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<TimetableDto>> getTimetable(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(adminPanelService.getTimetable(userDetails));
    }

    @Override
    public ResponseEntity<List<TeacherSubjectsDto>> getAllSubjects(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(adminPanelService.getAllSubjects(userDetails));
    }

    @Override
    public ResponseEntity<SubjectDto> createSubject(UserDetailsImpl userDetails, SubjectCreateDto subjectDto) {
        return ResponseEntity.ok(adminPanelService.createSubject(subjectDto, userDetails));
    }

    @Override
    public ResponseEntity<SubjectDto> updateSubject(UserDetailsImpl userDetails, int id, SubjectCreateDto subjectDto) {
        return ResponseEntity.ok(adminPanelService.updateSubject(id, subjectDto, userDetails));
    }

    @Override
    public ResponseEntity<TeacherSubjectDto> setTeacherToSubject(
            UserDetailsImpl userDetails, TeacherSubjectCreateDto teacherSubjectCreateDto) {
        return ResponseEntity.ok(adminPanelService.setTeacherToSubject(teacherSubjectCreateDto, userDetails));
    }

    @Override
    public ResponseEntity<TeacherSubjectDto> deleteTeacherToSubject(
            UserDetailsImpl userDetails, int teacherSubjectId) {
        adminPanelService.deleteTeacherToSubject(teacherSubjectId, userDetails);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SchoolProfileDto> getSchool(
            UserDetailsImpl userDetails) {
        return ResponseEntity.ok(adminPanelService.getSchool(userDetails));
    }

    @Override
    public ResponseEntity<SchoolProfileDto> updateSchool(
            UserDetailsImpl userDetails, SchoolWasCreatedDto schoolWasCreatedDto)  {
        return ResponseEntity.ok(adminPanelService.updateSchool(schoolWasCreatedDto, userDetails));
    }

    @Override
    public ResponseEntity editUser(UserDetailsImpl userDetails, int id, EditUserRoleDto editUserRoleDto) {
        adminPanelService.editUser(userDetails, id, editUserRoleDto);
        return ResponseEntity.noContent().build();
    }
}
