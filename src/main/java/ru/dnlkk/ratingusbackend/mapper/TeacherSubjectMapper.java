package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.teacher_subject.TeacherSubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleSimpleDto;
import ru.dnlkk.ratingusbackend.mapper.user_role.UserRoleMapper;
import ru.dnlkk.ratingusbackend.model.Subject;
import ru.dnlkk.ratingusbackend.model.TeacherSubject;
import ru.dnlkk.ratingusbackend.model.UserRole;

@Mapper
public interface TeacherSubjectMapper {
    TeacherSubjectMapper INSTANCE = Mappers.getMapper(TeacherSubjectMapper.class);

    @Mappings({
            @Mapping(target = "subject", source = "subject", qualifiedByName = "createSubjectDto"),
            @Mapping(target = "teacher", source = "teacher", qualifiedByName = "createUserRoleSimpleDto"),
    })
    TeacherSubjectDto toTeacherSubjectDto(TeacherSubject teacherSubject);

    @Named("createSubjectDto")
    static SubjectDto createSubjectDto(Subject subject) {
        return SubjectMapper.INSTANCE.toDto(subject);
    }

    @Named("createUserRoleSimpleDto")
    static UserRoleSimpleDto createUserRoleSimpleDto(UserRole userRole) {
        return UserRoleMapper.INSTANCE.toUserRoleSimpleDto(userRole);
    }
}
