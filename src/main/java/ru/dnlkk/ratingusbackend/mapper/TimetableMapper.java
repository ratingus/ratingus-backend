package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;
import ru.dnlkk.ratingusbackend.model.Timetable;

import java.util.List;

@Mapper
public interface TimetableMapper {
    TimetableMapper INSTANCE = Mappers.getMapper(TimetableMapper.class);

    TimetableDto toDto(Timetable timetable);
    Timetable toEntity(TimetableDto timetableDto);

    List<TimetableDto> toDtoList(List<Timetable> timetableList);
    List<Timetable> toEntityList(List<TimetableDto> timetableDtoList);
}
