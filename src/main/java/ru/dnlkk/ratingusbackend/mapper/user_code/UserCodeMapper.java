package ru.dnlkk.ratingusbackend.mapper.user_code;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeDto;
import ru.dnlkk.ratingusbackend.mapper.user.UserWithLoginMapper;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.util.List;

@Mapper
public interface UserCodeMapper {
    UserCodeMapper INSTANCE = Mappers.getMapper(UserCodeMapper.class);

    @Mapping(target = "userClassName", source = "userClass.name")
    @Mapping(target = "userWithLoginDto", source = "user", qualifiedByName = "getUserWithLoginDto")
    @Mapping(target = "schoolName", source = "school.name")
    @Mapping(target = "userRole", source = "userRole.role")
    UserCodeDto toUserCodeDto(UserCode userCode);

    @Named("getUserWithLoginDto")
    static UserWithLoginDto getUserWithLoginDto(User u) {
        return UserWithLoginMapper.INSTANCE.toDto(u);
    }

    @IterableMapping(elementTargetType = UserCodeDto.class)
    List<UserCodeDto> toUserCodeDtoList(List<UserCode> userCodeList);

}
