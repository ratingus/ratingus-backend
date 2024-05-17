package ru.dnlkk.ratingusbackend.service;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.*;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.mapper.ClassMapper;
import ru.dnlkk.ratingusbackend.mapper.SubjectMapper;
import ru.dnlkk.ratingusbackend.mapper.TimetableMapper;
import ru.dnlkk.ratingusbackend.mapper.user_code.UserCodeMapper;
import ru.dnlkk.ratingusbackend.mapper.user_role.UserRoleMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminPanelService {
    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final UserCodeRepository userCodeRepository;
    private final TimetableRepository timetableRepository;
    private final SchoolRepository schoolRepository;
    private final SubjectRepository subjectRepository;

    public List<UserRoleDto> getAllUsersRolesForSchool(int schoolId) {
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<UserRole> userRoles = school.get().getUserRoles();
            System.out.println(userRoles.get(0).toString());
            System.out.println(userRoles.get(0).getUser());
            return UserRoleMapper.INSTANCE.toDtoList(userRoles); //todo;
        }
    }

    public List<UserCodeDto> getAllUsersCodesForSchool(int schoolId) {
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<UserCode> userCodes = school.get().getUserCodes();
            return UserCodeMapper.INSTANCE.toUserCodeDtoList(userCodes); //todo;
        }
    }

    public UserCodeCreateDto createUserCode(UserCodeCreateDto userCodeCreateDto) {
        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeCreateDto);

        UUID uuid = Generators.nameBasedGenerator().generate(userCode.getUserClass().toString());
        //todo: можно сократить код (оставляем несколько цифр, в начале и конце - добавляем id пользователя и школы)
        userCode.setCode(uuid.toString());

        UserCode userCodeAfterSaving =
                userCodeRepository.saveAndFlush(userCode);
        return UserCodeMapper.INSTANCE.toUserCodeCreateDto(userCodeAfterSaving);
    }

    public UserCodeCreateDto updateUserCode(int userCodeId, UserCodeCreateDto userCodeCreateDto, int schoolId) {
        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeCreateDto);
        userCode.setId(userCodeId);
        UserCode userCodeAfterSaving = userCodeRepository.save(userCode); //проверить работу апдейта
//        int userCodeId = userCode.getId();
        //todo: проверить, что schoolId совпадает
        return UserCodeMapper.INSTANCE.toUserCodeCreateDto(userCodeAfterSaving);
    }

    public List<ClassDto> getAllClassesForSchool(int schoolId) {
        List<Class> classesBySchoolId = classRepository.findClassesBySchoolId(schoolId);
        return ClassMapper.INSTANCE.toClassDtoList(classesBySchoolId);
    }

    public ClassDto createClass(ClassDto classDto, int schoolId) {
        Class classEntity = ClassMapper.INSTANCE.toClassEntity(classDto);
        classEntity.setSchool(new School()); //todo: посмотреть, как это можно вынести в маппер
        classEntity.getSchool().setId(schoolId);
        Class classFromSaving = classRepository.saveAndFlush(classEntity);
        return ClassMapper.INSTANCE.toClassDto(classFromSaving);
    }

    public void deleteClass(int id) {
        classRepository.deleteById(id);
    }

    public List<TimetableDto> getTimetable(int schoolId) {
        List<Timetable> timetablesBySchoolId = timetableRepository.findTimetablesBySchoolId(schoolId);
        return TimetableMapper.INSTANCE.toDtoList(timetablesBySchoolId);
    }

    public SubjectDto createSubject(SubjectDto subjectDto, int schoolId) {
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDto);
        School s = new School();
        s.setId(schoolId);
        subject.setSchool(s);
        Subject savedSubject = subjectRepository.saveAndFlush(subject);
        return SubjectMapper.INSTANCE.toDto(savedSubject);
    }
}
