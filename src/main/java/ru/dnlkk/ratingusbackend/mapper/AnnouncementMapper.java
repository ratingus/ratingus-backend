package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.model.AnnouncementDto;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface AnnouncementMapper {
    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);



    Announcement toModel(AnnouncementDto announcementDto);



    @Mapping(target = "userId", source = "creator", qualifiedByName = "idFromUser")
    @Mapping(target = "classesId", source = "classes", qualifiedByName = "idFromClasses")
    List<AnnouncementDto> toDtoList(List<Announcement> announcementList);

    @Mapping(target = "userId", source = "creator", qualifiedByName = "idFromUser")
    @Mapping(target = "classesId", source = "classes", qualifiedByName = "idFromClasses")
    AnnouncementDto toDto(Announcement announcement);



    @Named("idFromUser")
    static Integer idFromUser(User user) {
        return user.getId();
    }
    @Named("idFromClasses")
    static List<Integer> idFromClasses(List<Class> classes) {
        List<Integer> ids = new ArrayList<>(classes.size());
        for (Class c : classes) {
            ids.add(c.getId());
        }
        return ids;
    }
}
