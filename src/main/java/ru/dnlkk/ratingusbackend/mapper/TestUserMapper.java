package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TestUserMapper { //todo: проверить, как упростить класс, после написания
    TestUserMapper INSTANCE = Mappers.getMapper(TestUserMapper.class);
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "announcements", source = "announcementsId", qualifiedByName = "getAnnouncementsFromIdList")
//    @Mapping(target = "studentsLessons", source = "studentsLessonsId", qualifiedByName = "getIdList")
//    @Mapping(target = "usersCodes", source = "usersCodesId", qualifiedByName = "getIdList")
//    @Mapping(target = "classes", source = "classesId", qualifiedByName = "getIdList")
//    @Mapping(target = "usersRoles", source = "usersRolesId", qualifiedByName = "getIdList")
//    @Mapping(target = "subjects", source = "subjectsId", qualifiedByName = "getIdList")
//    User toModel(UserDto userDto);

    @Named("getAnnouncementsFromIdList")
    static List<User> getAnnouncementsFromIdList(List<UserDto> userDtos) {
        List<User> resList = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            User u = new User();
            u.setId(userDto.getId());
            resList.add(u);
        }
        return resList; //todo: можно тоже обобщить. И
        //todo с помощью лямбд? Или в любом случае сделать проверку на null?
    }

//    List<User> toModelList(List<UserDto> userDtoList);

//    @Mapping(target = "announcementsId", source = "announcements", qualifiedByName = "getIdList")
//    @Mapping(target = "studentsLessonsId", source = "studentsLessons", qualifiedByName = "getIdList")
//    @Mapping(target = "usersCodesId", source = "usersCodes", qualifiedByName = "getIdList")
//    @Mapping(target = "classesId", source = "classes", qualifiedByName = "getIdList")
//    @Mapping(target = "usersRolesId", source = "usersRoles", qualifiedByName = "getIdList")
//    @Mapping(target = "subjectsId", source = "subjects", qualifiedByName = "getIdList")
//    UserDto toDto(User user);

    @Named("getIdList")
    static <T extends IdGettable> List<Integer> getIdList(List<T> objects) {
        return objects.stream()
                .map(IdGettable::getId)
                .collect(Collectors.toList());
    }

    @IterableMapping(elementTargetType = UserDto.class)
    List<UserDto> toDtoList(List<User> userList);
}
