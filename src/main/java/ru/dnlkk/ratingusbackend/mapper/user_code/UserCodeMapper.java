package ru.dnlkk.ratingusbackend.mapper.user_code;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
//import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;
import ru.dnlkk.ratingusbackend.mapper.ClassMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserCodeMapper {
    UserCodeMapper INSTANCE = Mappers.getMapper(UserCodeMapper.class);

    @Mapping(target = "classDto", source = "userClass", qualifiedByName = "getDtoFromClass")
    UserCodeWithClassDto toUserCodeWithClassDto(UserCode userCode);

    @IterableMapping(elementTargetType = UserCodeWithClassDto.class)
    List<UserCodeWithClassDto> toUserCodeWithClassDtoList(List<UserCode> userCodeList);

    @Mappings({
            @Mapping(target = "userClass", source = "classDto", qualifiedByName = "createClassFromDto"),
            @Mapping(target = "school", expression = "java(createSchool())"),
            @Mapping(target = "creator", expression = "java(createCreator())"),

//            @Mapping(target = "school", qualifiedByName = "createSchool"),
//            @Mapping(target = "creator", qualifiedByName = "createCreator"),
    })
    UserCode toUserCode(UserCodeWithClassDto userCodeWithClassDto);

    @Named("createSchool")
    default School createSchool() {
        return new School();
    }

    @Named("createClassFromDto")
    static Class createClassFromDto(ClassDto classDto) {
        return ClassMapper.INSTANCE.toClassEntity(classDto);
    }

    @Named("getDtoFromClass")
    static ClassDto getDtoFromClass(Class c) {
        return ClassMapper.INSTANCE.toClassDto(c);
    }

    @Named("createCreator")
    default User createCreator() {
        return new User();
    }

















    @Mappings({
            @Mapping(target = "userClassName", source = "userClass.name"),
            @Mapping(target = "userRole", source = "role"),
    })
    UserCodeDto toUserCodeDto(UserCode userCode);
    @IterableMapping(elementTargetType = UserCodeDto.class)
    List<UserCodeDto> toUserCodeDtoList(List<UserCode> userCodeList);



    @Mappings({
            @Mapping(target = "userClassId", source = "userClass", qualifiedByName = "getIdFromEntity"),
    })
    UserCodeCreateDto toUserCodeCreateDto(UserCode userCode);

    @Mappings({
            @Mapping(target = "userClass", source = "userClassId", qualifiedByName = "getUserClass"),
            @Mapping(target = "school", expression = "java(createSchool())"),
            @Mapping(target = "creator", expression = "java(createCreator())"),
    })
    UserCode toUserCode(UserCodeCreateDto userCodeCreateDto);

//    @Named("createSchool")
//    static School createSchool() {
//        return new School();
//    }
//
//    @Named("createCreator")
//    static User createCreator() {
//        return new User();
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
