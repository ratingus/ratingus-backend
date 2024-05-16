package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.model.Subject;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
    Subject toEntity(SubjectDto subjectDto);
    SubjectDto toDto(Subject subject);
}