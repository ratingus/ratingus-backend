package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.profile.SchoolDto;
import ru.dnlkk.ratingusbackend.api.dtos.school.SchoolWasCreatedDto;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.School;

import java.util.List;

@Mapper
public interface SchoolMapper {
    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    SchoolWasCreatedDto toSchoolWasCreatedDto(School school);

    @IterableMapping(elementTargetType = SchoolWasCreatedDto.class)
    List<SchoolWasCreatedDto> toSchoolDtoList(List<School> schoolList);
}
