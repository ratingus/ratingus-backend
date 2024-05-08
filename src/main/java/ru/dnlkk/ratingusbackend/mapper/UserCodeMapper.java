package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCodeMapper {
    UserCodeMapper INSTANCE = Mappers.getMapper(UserCodeMapper.class);
}
