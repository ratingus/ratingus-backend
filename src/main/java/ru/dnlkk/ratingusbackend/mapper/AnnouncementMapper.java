package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.model.AnnouncementDto;
import ru.dnlkk.ratingusbackend.model.Announcement;

@Mapper
public interface AnnouncementMapper {
    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);
    Announcement toModel(AnnouncementDto announcementDto);
    AnnouncementDto toDto(Announcement announcement);
}
