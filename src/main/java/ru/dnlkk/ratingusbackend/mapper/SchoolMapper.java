package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.model.School;

@Mapper
public interface SchoolMapper {
    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    SchoolWasCreatedDto toSchoolWasCreatedDto(School school);
}
