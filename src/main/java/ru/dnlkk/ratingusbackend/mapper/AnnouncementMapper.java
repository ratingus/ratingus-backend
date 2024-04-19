package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.model.AnnouncementDto;
import ru.dnlkk.ratingusbackend.model.Announcement;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    Announcement toModel(AnnouncementDto announcementDto);
    @Mapping(target = "creator", ignore = true)
    AnnouncementDto toDto(Announcement announcement);

    List<Announcement> toModelList(List<AnnouncementDto> announcementDtoList);
    @Mapping(target = "creator", ignore = true)
    List<AnnouncementDto> toDtoList(List<Announcement> announcementList);
}
