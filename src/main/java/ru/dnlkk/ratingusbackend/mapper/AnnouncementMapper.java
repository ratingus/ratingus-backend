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


    @Mapping(target = "classes", source = "classesId", qualifiedByName = "getClassListFromIdsList")
    @Mapping(target = "creator", source = "userId", qualifiedByName = "getUserFromId")
    Announcement toModel(AnnouncementDto announcementDto);

    @Named("getClassListFromIdsList")
    static List<Class> getClassListFromIdsList(List<Integer> classesId) {
        List<Class> classes = new ArrayList<>(classesId.size());
        for (Integer integer : classesId) {
            Class c = new Class();
            c.setId(integer);
            classes.add(c);
        }
        return classes;
    }
    @Named("getUserFromId")
    static User getUserFromId(int userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }



    @Mapping(target = "userId", source = "creator.id")
    @Mapping(target = "classesId", source = "classes", qualifiedByName = "idFromClasses")
    List<AnnouncementDto> toDtoList(List<Announcement> announcementList);

    @Mapping(target = "userId", source = "creator.id") //, qualifiedByName = "idFromUser"
    @Mapping(target = "classesId", source = "classes", qualifiedByName = "idFromClasses")
    AnnouncementDto toDto(Announcement announcement);


    @Named("idFromClasses")
    static List<Integer> idFromClasses(List<Class> classes) {
        List<Integer> ids = new ArrayList<>(classes.size());
        for (Class c : classes) {
            ids.add(c.getId());
        }
        return ids;
    }
}
