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

    private final String SOME_TEXT = "some_text";

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

    public List<UserCodeWithClassDto> getAllUsersCodesForSchool(int schoolId) {
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<UserCode> userCodes = school.get().getUserCodes();
            return UserCodeMapper.INSTANCE.toUserCodeWithClassDtoList(userCodes); //todo;
        }
    }

    public UserCodeWithClassDto createUserCode(UserCodeWithClassDto userCodeWithClassDto, int creatorId, int schoolId) {
        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeWithClassDto);

        userCode.getCreator().setId(creatorId);
        userCode.getSchool().setId(schoolId);

        checkIsClassCorrectInUserCode(userCode, schoolId);

        String code = generateUniqueCode();
        userCode.setCode(code);

        UserCode userCodeAfterSaving = userCodeRepository.saveAndFlush(userCode);
        return UserCodeMapper.INSTANCE.toUserCodeWithClassDto(userCodeAfterSaving);
    }

    public UserCodeWithClassDto updateUserCode(int userCodeId, UserCodeWithClassDto userCodeWithClassDto, int schoolId) {
        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeWithClassDto);
        userCode.setId(userCodeId);
        userCode.getSchool().setId(schoolId);

        checkIsClassCorrectInUserCode(userCode, schoolId);

        UserCode userCodeAfterSaving = userCodeRepository.save(userCode); //проверить работу апдейта
        return UserCodeMapper.INSTANCE.toUserCodeWithClassDto(userCodeAfterSaving);
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

    private void checkIsClassCorrectInUserCode(UserCode userCode, int schoolId) {
        if (userCode.getRole() == Role.STUDENT) {
            if (userCode.getUserClass() == null) {
                throw new RuntimeException("Учащемуся не был присвоен класс");
            } else {
                Optional<Class> classById = classRepository.findById(userCode.getUserClass().getId());
                if (classById.isEmpty()) {
                    throw new RuntimeException("Нет класса с таким id");
                } else {
                    Class c = classById.get();
                    if (c.getSchool().getId() != schoolId) {
                        throw new RuntimeException("В школе нет класса с таким id");
                    } else {
                        userCode.setUserClass(c);
                    }
                }
            }
        }
    }

    private String generateUniqueCode() {
        //todo: можно сократить код (оставляем несколько цифр, в начале и конце - добавляем id пользователя и школы)
        return Generators.nameBasedGenerator().generate(SOME_TEXT).toString();
    }
}
