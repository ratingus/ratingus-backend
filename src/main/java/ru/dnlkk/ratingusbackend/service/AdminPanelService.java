package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectsDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherWithSubjectIdDto;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleSimpleDto;
import ru.dnlkk.ratingusbackend.exceptions.ForbiddenException;
import ru.dnlkk.ratingusbackend.exceptions.LogicException;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;
import ru.dnlkk.ratingusbackend.mapper.ClassMapper;
import ru.dnlkk.ratingusbackend.mapper.SubjectMapper;
import ru.dnlkk.ratingusbackend.mapper.TeacherSubjectMapper;
import ru.dnlkk.ratingusbackend.mapper.TimetableMapper;
import ru.dnlkk.ratingusbackend.mapper.user_code.UserCodeMapper;
import ru.dnlkk.ratingusbackend.mapper.user_role.UserRoleMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.repository.*;
import ru.dnlkk.ratingusbackend.service.util.RandomSequenceGenerator;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPanelService {
    private final ClassRepository classRepository;
    private final UserCodeRepository userCodeRepository;
    private final TimetableRepository timetableRepository;
    private final SchoolRepository schoolRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final UserRoleRepository userRoleRepository;

    private void forbidAccessForNullUserRole(UserDetailsImpl userDetails) {
        if (Boolean.TRUE.equals(userDetails.getUser().getIsAdmin())) { //todo: не до конца всё проверяет и работает (если у админа userRole=null, проблема не решена. Но такого и не должно быть)
            return;
        }
        if (userDetails.getUserRole() == null || (userDetails.getUserRole().getRole() != Role.LOCAL_ADMIN)) {
            throw new ForbiddenException("Доступ запрещён");
        }
    }

    public List<UserRoleDto> getAllUsersRolesForSchool(UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<UserRole> userRoles = school.get().getUserRoles();
            return UserRoleMapper.INSTANCE.toDtoList(userRoles);
        }
    }

    public List<UserRoleSimpleDto> getAllTeachersForSchool(UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        List<UserRole> userRolesBySchoolIdAndRole = userRoleRepository.findUserRolesBySchoolIdAndRole(schoolId, Role.TEACHER);
        return UserRoleMapper.INSTANCE.toUserRoleSimpleDtoList(userRolesBySchoolIdAndRole);
    }

    public List<UserCodeWithClassDto> getAllUsersCodesForSchool(UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<UserCode> userCodes = school.get().getUserCodes();
            return UserCodeMapper.INSTANCE.toUserCodeWithClassDtoList(userCodes);
        }
    }

    public UserCodeWithClassDto createUserCode(UserCodeWithClassDto userCodeWithClassDto, UserDetailsImpl userDetails, Integer school) {
        forbidAccessForNullUserRole(userDetails);
        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeWithClassDto);
        int schoolId = school != null ? school : userDetails.getUserRole().getSchool().getId();

        userCode.setCreator(userDetails.getUser());
        userCode.getSchool().setId(schoolId);

        checkIsClassCorrectInUserCode(userCode, schoolId);

        userCode.setCode("temp_code");
        UserCode userCodeAfterSaving = userCodeRepository.saveAndFlush(userCode);
        String code = generateUniqueCodeById(userCode.getId());
        userCodeAfterSaving.setCode(code);
        UserCode finalUserCode = userCodeRepository.save(userCodeAfterSaving);

        return UserCodeMapper.INSTANCE.toUserCodeWithClassDto(finalUserCode);
    }

    public UserCodeWithClassDto updateUserCode(int userCodeId, UserCodeWithClassDto userCodeWithClassDto, UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        UserCode userCode = UserCodeMapper.INSTANCE.toUserCode(userCodeWithClassDto);
        int schoolId = userDetails.getUserRole().getSchool().getId();

        Optional<UserCode> userCodeById = userCodeRepository.findById(userCodeId);
        if (userCodeById.isEmpty()) {
            throw new NotFoundException("Нет пользователя с id " + userCodeId);
        } else {
            UserCode userCodeFromRepo = userCodeById.get();
            if (userCodeFromRepo.getSchool().getId() != schoolId) {
                throw new ForbiddenException("Нет доступа к пользователю с id " + userCodeId);
            } else {
                userCode.getCreator().setId(userCodeFromRepo.getCreator().getId());
            }
        }

        if (userCode.getCode() == null) {
            userCode.setCode(generateUniqueCodeById(userCodeId));
        }

        userCode.setId(userCodeId);
        userCode.getSchool().setId(schoolId);

        checkIsClassCorrectInUserCode(userCode, schoolId);

        UserCode userCodeAfterSaving = userCodeRepository.save(userCode); //проверить работу апдейта
        return UserCodeMapper.INSTANCE.toUserCodeWithClassDto(userCodeAfterSaving);
    }

    public List<ClassDto> getAllClassesForSchool(UserDetailsImpl userDetails) {
        int schoolId = userDetails.getUserRole().getSchool().getId();
        List<Class> classesBySchoolId = classRepository.findClassesBySchoolId(schoolId);
        return ClassMapper.INSTANCE.toClassDtoList(classesBySchoolId);
    }

    public ClassDto createClass(ClassDto classDto, UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        Class classEntity = ClassMapper.INSTANCE.toClassEntity(classDto);
        School school = schoolRepository.findById(schoolId).get();
        classEntity.setSchool(school);
        Class classFromSaving = classRepository.saveAndFlush(classEntity);
        return ClassMapper.INSTANCE.toClassDto(classFromSaving);
    }

    public void deleteClass(int id, UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        Optional<Class> byId = classRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("Не найден класс с id=" + id);
        }
        Class cl = byId.get();
        if (cl.getSchool().getId() != schoolId) {
            throw new ForbiddenException("Нет доступа к классу с id=" + id);
        }
        classRepository.deleteById(id);
    }

    public List<TimetableDto> getTimetable(UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        List<Timetable> timetablesBySchoolId = timetableRepository.findTimetablesBySchoolId(schoolId);
        return TimetableMapper.INSTANCE.toDtoList(timetablesBySchoolId);
    }

    public List<TeacherSubjectsDto> getAllSubjects(UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        List<Subject> subjects = subjectRepository.findAllBySchool_Id(schoolId);
        List<Integer> subjectIds = subjects.stream().map(Subject::getId).toList();
        List<TeacherSubject> teacherSubjects = teacherSubjectRepository.findBySubjectIds(subjectIds);

        // Создаем мапу, где ключ - это ID предмета, а значение - список учителей этого предмета
        Map<Integer, List<TeacherWithSubjectIdDto>> subjectTeachersMap = new HashMap<>();
        for (TeacherSubject teacherSubject : teacherSubjects) {
            int subjectId = teacherSubject.getSubject().getId();
            TeacherWithSubjectIdDto teacherDto = new TeacherWithSubjectIdDto(
                    teacherSubject.getId(),
                    teacherSubject.getTeacher().getId(),
                    teacherSubject.getTeacher().getName(),
                    teacherSubject.getTeacher().getSurname(),
                    teacherSubject.getTeacher().getPatronymic()
            );
            subjectTeachersMap.computeIfAbsent(subjectId, k -> new ArrayList<>()).add(teacherDto);
        }

        List<TeacherSubjectsDto> res = new ArrayList<>();
        for (Subject subject : subjects) {
            SubjectDto subjectDto = SubjectMapper.INSTANCE.toDto(subject);
            TeacherSubjectsDto teacherSubjectsDto = new TeacherSubjectsDto();
            teacherSubjectsDto.setSubject(subjectDto);
            teacherSubjectsDto.setTeachers(subjectTeachersMap.getOrDefault(subject.getId(), null));
            res.add(teacherSubjectsDto);
        }

        return res;
    }

    public SubjectDto createSubject(SubjectCreateDto subjectDto, UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        School school = schoolRepository.findById(schoolId).get();
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDto);
        subject.setSchool(school);
        Subject subjectAfterSaving = subjectRepository.saveAndFlush(subject);
        return SubjectMapper.INSTANCE.toDto(subjectAfterSaving);
    }

    public TeacherSubjectDto setTeacherToSubject(TeacherSubjectCreateDto teacherSubjectCreateDto, UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();

        int subjectId = teacherSubjectCreateDto.getSubjId();
        int teacherId = teacherSubjectCreateDto.getTeacherId();

        checkIsSubjectCorrectAndAvailableForSchool(subjectId, schoolId);
        Subject subject = subjectRepository.findById(subjectId).get();

        Optional<UserRole> teachOptional = userRoleRepository.findById(teacherId);
        if (teachOptional.isEmpty()) {
            throw new NotFoundException("Нет учителя с id=" + teacherId);
        }
        UserRole teacher = teachOptional.get();

        if (teacher.getSchool().getId() != schoolId) {
            throw new ForbiddenException("Нет доступа к учителю с id=" + teacherId);
        }

        Optional<TeacherSubject> teacherSubjectsBySubjectId = teacherSubjectRepository
                .findTeacherSubjectsBySubjectIdAndTeacherId(subjectId, teacherId);

        if (teacherSubjectsBySubjectId.isPresent()) {
            throw new LogicException("Связь предмета с id=" + subjectId + " с учителем с id=" + teacherId + " уже существует");
        }

        TeacherSubject teacherSubject = new TeacherSubject();
        teacherSubject.setSubject(subject);
        teacherSubject.setTeacher(teacher);

        TeacherSubject teacherSubjectAfterSaving = teacherSubjectRepository.saveAndFlush(teacherSubject);

        return TeacherSubjectMapper.INSTANCE.toTeacherSubjectDto(teacherSubjectAfterSaving);
    }

    public void deleteTeacherToSubject(int teacherSubjectId, UserDetailsImpl userDetails) {
        forbidAccessForNullUserRole(userDetails);
        int schoolId = userDetails.getUserRole().getSchool().getId();
        Optional<TeacherSubject> byId = teacherSubjectRepository.findById(teacherSubjectId);
        if (byId.isEmpty()) {
            throw new NotFoundException("Нет связи учителя с классом с id=" + teacherSubjectId);
        }
        TeacherSubject teacherSubject = byId.get();
        int teacherSchoolId = userRoleRepository.findById(
                teacherSubject.getTeacher().getId())
                .get()
                .getSchool()
                .getId();
        if (teacherSchoolId != schoolId) {
            throw new ForbiddenException("Нет доступа к связи предмет-учитель с id=" + teacherSchoolId);
        }
        teacherSubjectRepository.deleteById(teacherSubjectId);
    }

    private void checkIsSubjectCorrectAndAvailableForSchool(int subjId, int schoolId) {
        Optional<Subject> byIdFromRepo = subjectRepository.findById(subjId);
        if (byIdFromRepo.isEmpty()) {
            throw new NotFoundException("Предмет с id=" + subjId + " не найден");
        }
        Subject subjFromRepo = byIdFromRepo.get();
        if (subjFromRepo.getSchool().getId() != schoolId) {
            throw new ForbiddenException("Нет доступа к предмету с id=" + subjId);
        }
    }

    private void checkIsClassCorrectInUserCode(UserCode userCode, int schoolId) {
        if (userCode.getRole() == Role.STUDENT) {
            if (userCode.getUserClass() == null) {
                throw new RuntimeException("Учащемуся не был присвоен класс");
            } else {
                int classId = userCode.getUserClass().getId();
                Optional<Class> classById = classRepository.findById(classId);
                if (classById.isEmpty()) {
                    throw new NotFoundException("Нет класса с id=" + classId);
                } else {
                    Class c = classById.get();
                    if (c.getSchool().getId() != schoolId) {
                        throw new ForbiddenException("Нет доступа к классу с id=" + classId);
                    } else {
                        userCode.setUserClass(c);
                    }
                }
            }
        } else {
            if (userCode.getUserClass() != null) {
                throw new LogicException("Пользователю с ролью " + userCode.getRole() + " не может быть присвоен класс");
            }
        }
    }

    private String generateUniqueCodeById(int id) {
//        return Generators.nameBasedGenerator().generate(str).toString();
        return RandomSequenceGenerator.generateRandomSequence() + id;
    }
}
