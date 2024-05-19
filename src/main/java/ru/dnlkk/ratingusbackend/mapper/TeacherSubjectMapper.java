package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleSimpleDto;
import ru.dnlkk.ratingusbackend.mapper.user_role.UserRoleMapper;
import ru.dnlkk.ratingusbackend.model.Subject;
import ru.dnlkk.ratingusbackend.model.TeacherSubject;
import ru.dnlkk.ratingusbackend.model.UserRole;

import java.util.List;

@Mapper
public interface TeacherSubjectMapper {
    TeacherSubjectMapper INSTANCE = Mappers.getMapper(TeacherSubjectMapper.class);

    @Mappings({
            @Mapping(target = "subject", source = "subject", qualifiedByName = "createSubjectDto"),
            @Mapping(target = "teacher", source = "teacher", qualifiedByName = "createUserRoleSimpleDto"),
    })
    TeacherSubjectDto toTeacherSubjectDto(TeacherSubject teacherSubject);

    @IterableMapping(elementTargetType = TeacherSubjectDto.class)
    List<TeacherSubjectDto> toTeacherSubjectDtoList(List<TeacherSubject> teacherSubject);

    @Named("createSubjectDto")
    static SubjectDto createSubjectDto(Subject subject) {
        return SubjectMapper.INSTANCE.toDto(subject);
    }

    @Named("createUserRoleSimpleDto")
    static UserRoleSimpleDto createUserRoleSimpleDto(UserRole userRole) {
        return UserRoleMapper.INSTANCE.toUserRoleSimpleDto(userRole);
    }

    @Mapping(target = "teacher", ignore = true)
    TeacherSubjectDto toTeacherSubjectDtoFromSubject(Subject subject);

    @IterableMapping(elementTargetType = TeacherSubjectDto.class)
    List<TeacherSubjectDto> toTeacherSubjectDtoListFromSubjectList(List<Subject> subjects);
}
