package ru.dnlkk.ratingusbackend.mapper.user_code;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeViewDto;
import ru.dnlkk.ratingusbackend.mapper.user.UserWithLoginMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;

import java.util.List;

@Mapper
public interface UserCodeMapper {
    UserCodeMapper INSTANCE = Mappers.getMapper(UserCodeMapper.class);



    @Mappings({
            @Mapping(target = "userClassName", source = "userClass.name"),
            @Mapping(target = "userWithLoginDto", source = "user", qualifiedByName = "getUserWithLoginDto"),
            @Mapping(target = "schoolName", source = "school.name"),
            @Mapping(target = "userRole", source = "userRole.role")
    })
    UserCodeViewDto toUserCodeViewDto(UserCode userCode);

    @Named("getUserWithLoginDto")
    static UserWithLoginDto getUserWithLoginDto(User u) {
        return UserWithLoginMapper.INSTANCE.toDto(u);
    }

    @IterableMapping(elementTargetType = UserCodeViewDto.class)
    List<UserCodeViewDto> toUserCodeDtoList(List<UserCode> userCodeList);



    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "activated", ignore = true),
            @Mapping(target = "userClass", source = "userClassId", qualifiedByName = "getUserClass"),
            @Mapping(target = "user", source = "login", qualifiedByName = "getUserByLogin"),
            @Mapping(target = "creator", source = "creatorId", qualifiedByName = "getCreator"),
            @Mapping(target = "school", source = "schoolId", qualifiedByName = "getSchool"),
    })
    UserCode toUserCode(UserCodeCreateDto userCodeCreateDto);

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



//    @Mappings({
//            @Mapping(target = "userClass", source = "userClass.name"),
//            @Mapping(target = "userWithLoginDto", source = "user", qualifiedByName = "getUserWithLoginDto"),
//            @Mapping(target = "schoolName", source = "school.name"),
//            @Mapping(target = "userRole", source = "userRole.role")
//    })
//    UserCodeCreateDto toUserCodeCreateDto(UserCode userCode);


}
