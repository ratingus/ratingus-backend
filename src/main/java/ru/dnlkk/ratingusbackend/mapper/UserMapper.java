package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper { //todo: проверить, как упростить класс, после написания
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//    @Mapping(target = "password", ignore = true)
//    User toModel(UserDto userDto);
//    List<User> toModelList(List<UserDto> userDtoList);


//    @Mapping(target = ".", source = "password", ignore = true) //todo: проверить, работает ли (точка говорит "ничего")
    @Mapping(target = "announcementsId", source = "announcements", qualifiedByName = "getIdList")
    @Mapping(target = "studentsLessonsId", source = "studentsLessons", qualifiedByName = "getIdList")
    @Mapping(target = "usersCodesId", source = "usersCodes", qualifiedByName = "getIdList")
    @Mapping(target = "classesId", source = "classes", qualifiedByName = "getIdList")
    @Mapping(target = "usersRolesId", source = "usersRoles", qualifiedByName = "getIdList")
    @Mapping(target = "subjectsId", source = "subjects", qualifiedByName = "getIdList")
    UserDto toDto(User user);

    @Named("getIdList")
    static <T extends IdGettable> List<Integer> getIdList(List<T> objects) {
        return objects.stream()
                .map(IdGettable::getId)
                .collect(Collectors.toList());
    }

    @IterableMapping(elementTargetType = UserDto.class)
    List<UserDto> toDtoList(List<User> userList);
}
