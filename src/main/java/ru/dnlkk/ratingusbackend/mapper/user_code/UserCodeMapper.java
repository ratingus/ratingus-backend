package ru.dnlkk.ratingusbackend.mapper.user_code;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
//import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeViewDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.mapper.user.UserWithLoginMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserCodeMapper {
    UserCodeMapper INSTANCE = Mappers.getMapper(UserCodeMapper.class);

    @Mappings({
            @Mapping(target = "userClassName", source = "userClass.name"),
            @Mapping(target = "userRole", source = "role"),
    })
    UserCodeDto toUserCodeDto(UserCode userCode);
    @IterableMapping(elementTargetType = UserCodeDto.class)
    List<UserCodeDto> toUserCodeDtoList(List<UserCode> userCodeList);


//    @Mappings({
//            @Mapping(target = "userClassName", source = "userClass.name"),
//            @Mapping(target = "userWithLoginDto", source = "user", qualifiedByName = "getUserWithLoginDto"),
//            @Mapping(target = "schoolName", source = "school.name"),
//    })
//    UserCodeViewDto toUserCodeViewDto(UserCode userCode);

//    @IterableMapping(elementTargetType = UserCodeViewDto.class)
//    List<UserCodeViewDto> toUserCodeDtoList(List<UserCode> userCodeList);

    @Mappings({
            @Mapping(target = "userClassId", source = "userClass", qualifiedByName = "getIdFromEntity"),
            @Mapping(target = "creatorId", source = "creator", qualifiedByName = "getIdFromEntity"),
            @Mapping(target = "schoolId", source = "school", qualifiedByName = "getIdFromEntity"),
    })
    UserCodeCreateDto toUserCodeCreateDto(UserCode userCode);

    @Mappings({
            @Mapping(target = "userClass", source = "userClassId", qualifiedByName = "getUserClass"),
            @Mapping(target = "creator", source = "creatorId", qualifiedByName = "getCreator"),
            @Mapping(target = "school", source = "schoolId", qualifiedByName = "getSchool"),
//            @Mapping(target = "role", source = "userRole"),
    })
    UserCode toUserCode(UserCodeCreateDto userCodeCreateDto);

//    @Mappings({
//            @Mapping(target = "activated", ignore = true),
//            @Mapping(target = "userClass", source = "userClassId", qualifiedByName = "getUserClass"),
//            @Mapping(target = "creator", source = "creatorId", qualifiedByName = "getCreator"),
//            @Mapping(target = "school", source = "schoolId", qualifiedByName = "getSchool"),
//            @Mapping(target = "role", source = "userRole"),
//    })
//    UserCode toUserCode(UserCodeDto userCodeDto);

//    @Named("role")
//    static Role role(Role role) {
//        return role;
//    }
    @Named("getRoleFromUser")
    static Role getRoleFromUser(UserRole role) {
        return role.getRole();
    }

    @Named("getRoleFromDto")
    static UserRole getRoleFromDto(Role role) {
        UserRole ur = new UserRole();
        ur.setRole(role);
        return ur;
    }

    @Named("getUserClass")
    static Class getUserClass(int id) {
        Class entity = new Class();
        entity.setId(id);
        return entity;
    }

    @Named("getUserByLogin")
    static User getUserByLogin(String login) {
        User entity = new User();
        entity.setLogin(login);
        return entity;
    }

    @Named("getCreator")
    static User getCreator(int id) {
        User entity = new User();
        entity.setId(id);
        return entity;
    }

    @Named("getSchool")
    static School getSchool(int id) {
        School entity = new School();
        entity.setId(id);
        return entity;
    }

    @Named("getIdFromEntity")
    static <T extends IdGettable> Integer getIdFromEntity(T entity) {
        return entity.getId();
    }

    @Named("getIdFromList")
    static <T extends IdGettable> List<Integer> getIdList(List<T> objects) {
        return objects.stream()
                .map(IdGettable::getId)
                .collect(Collectors.toList());
    }

//    @Mappings({
//            @Mapping(target = "userClass", source = "userClass.name"),
//            @Mapping(target = "userWithLoginDto", source = "user", qualifiedByName = "getUserWithLoginDto"),
//            @Mapping(target = "schoolName", source = "school.name"),
//            @Mapping(target = "userRole", source = "userRole.role")
//    })
//    UserCodeCreateDto toUserCodeCreateDto(UserCode userCode);


}
