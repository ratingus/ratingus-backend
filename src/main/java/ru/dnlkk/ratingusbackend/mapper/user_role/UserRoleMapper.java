package ru.dnlkk.ratingusbackend.mapper.user_role;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.user.CreatorDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleSimpleDto;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    @Mappings({
            @Mapping(target = "login", source = "user.login"),
            @Mapping(target = "birthdate", source = "user.birthDate"),
            @Mapping(target = "schoolName", source = "school.name"),
            @Mapping(target = "userRole", source = "role"),
    })
    UserRoleDto toDto(UserRole userRole);

    @IterableMapping(elementTargetType = UserRoleDto.class)
    List<UserRoleDto> toDtoList(List<UserRole> userRoleList);

    UserRoleSimpleDto toUserRoleSimpleDto(UserRole userRole);

    CreatorDto toCreatorDto(UserRole user);

//    UserRole toEntity(UserRoleDto userRoleDto);


//    @IterableMapping(elementTargetType = UserRole.class)
//    List<UserRole> toEntityList(List<UserRoleDto> userRoleDtoList);
}
