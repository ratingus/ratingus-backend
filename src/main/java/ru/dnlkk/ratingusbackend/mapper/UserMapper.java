package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper { //todo: проверить, как упростить класс, после написания
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//    @Mapping(target = "password", ignore = true)
//    User toModel(UserDto userDto);
//    List<User> toModelList(List<UserDto> userDtoList);


//    @Mapping(target = ".", source = "password", ignore = true) //todo: проверить, работает ли (точка говорит "ничего")
    @Mapping(target = "announcementsId", source = "announcements", qualifiedByName = "idFromAnnouncements")
    @Mapping(target = "studentsLessonsId", source = "studentsLessons", qualifiedByName = "idFromStudentsLessons")
    @Mapping(target = "usersCodesId", source = "usersCodes", qualifiedByName = "idFromUsersCodes")
    @Mapping(target = "classesId", source = "classes", qualifiedByName = "idFromClasses")
    @Mapping(target = "usersRolesId", source = "usersRoles", qualifiedByName = "idFromUsersRoles")
    @Mapping(target = "subjectsId", source = "subjects", qualifiedByName = "idFromSubjects")
    UserDto toDto(User user);

    @Named("idFromAnnouncements")
    static List<Integer> idFromAnnouncements(List<Announcement> announcements) {
        List<Integer> ids = new ArrayList<>(announcements.size());
        for (Announcement a : announcements) {
            ids.add(a.getId());
        }
        return ids;
    }

    @Named("idFromStudentsLessons")
    static List<Integer> idFromStudentsLessons(List<StudentLesson> studentsLessons) {
        List<Integer> ids = new ArrayList<>(studentsLessons.size());
        for (StudentLesson sl : studentsLessons) {
            ids.add(sl.getId());
        }
        return ids;
    }

    @Named("idFromUsersCodes")
    static List<Integer> idFromUsersCodes(List<UserCode> userCodes) {
        List<Integer> ids = new ArrayList<>(userCodes.size());
        for (UserCode u : userCodes) {
            ids.add(u.getId());
        }
        return ids;
    }

    @Named("idFromClasses")
    static List<Integer> idFromClasses(List<Class> classes) {
        List<Integer> ids = new ArrayList<>(classes.size());
        for (Class c : classes) {
            ids.add(c.getId());
        }
        return ids;
    }

    @Named("idFromUsersRoles")
    static List<Integer> idFromUsersRoles(List<UserRole> userRoles) {
        List<Integer> ids = new ArrayList<>(userRoles.size());
        for (UserRole u : userRoles) {
            ids.add(u.getId());
        }
        return ids;
    }

    @Named("idFromSubjects")
    static List<Integer> idFromSubjects(List<Subject> subjects) {
        List<Integer> ids = new ArrayList<>(subjects.size());
        for (Subject s : subjects) {
            ids.add(s.getId());
        }
        return ids;
    }
    @IterableMapping(elementTargetType = UserDto.class)
    List<UserDto> toDtoList(List<User> userList);
}
