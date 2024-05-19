package ru.dnlkk.ratingusbackend.mapper.user;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.user.CreatorDto;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserForAdminPanelDto;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mappings({
//            @Mapping(target = "name", source = "userRole.name")
//    })
//    UserForAdminPanelDto toUserForAdminPanelDto(User user);
//    @IterableMapping(elementTargetType = UserForAdminPanelDto.class)
//    List<UserForAdminPanelDto> toUserForAdminPanelDtoList(List<User> userList);

    CreatorDto toCreatorDto(User user);


}
