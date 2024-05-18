package ru.dnlkk.ratingusbackend.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.model.User;

@Mapper
public interface UserWithLoginMapper {
    UserWithLoginMapper INSTANCE = Mappers.getMapper(UserWithLoginMapper.class);
    UserWithLoginDto toDto(User user);
}
