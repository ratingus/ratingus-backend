package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//    @Mapping(target = "", source = "", qualifiedByName = "")
    @Mapping(target = "password", ignore = true)
    User toModel(UserDto userDto);

    List<User> toModelList(List<UserDto> userDtoList);


//    @Mapping(target = "", source = "password", ignore = true) //todo: проверить, работает ли
//    @Mapping(target = "announcementsId", source = "announcements", qualifiedByName = "idFromAnnouncements")
    UserDto toDto(User user);

    @Named("idFromAnnouncements")
    static List<Integer> idFromAnnouncements(List<Announcement> announcements) {
        List<Integer> ids = new ArrayList<>(announcements.size());
        for (Announcement a : announcements) {
            ids.add(a.getId());
        }
        return ids;
    }

    List<UserDto> toDtoList(List<User> userList);
}
