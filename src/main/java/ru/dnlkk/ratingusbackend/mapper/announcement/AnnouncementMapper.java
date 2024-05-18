package ru.dnlkk.ratingusbackend.mapper.announcement;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.announcement.AnnouncementCreateDto;
import ru.dnlkk.ratingusbackend.api.dtos.announcement.AnnouncementDto;
import ru.dnlkk.ratingusbackend.api.dtos.user.CreatorDto;
import ru.dnlkk.ratingusbackend.mapper.user.UserMapper;
import ru.dnlkk.ratingusbackend.mapper.user_code.UserCodeMapper;
import ru.dnlkk.ratingusbackend.model.Announcement;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface AnnouncementMapper {
    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    @Mapping(target = "classes", source = "classesId", qualifiedByName = "getClassListFromIdsList")
    @Mapping(target = "creator", expression = "java(createCreator())")
    Announcement toModel(AnnouncementCreateDto announcementDto);

    @Named("createCreator()")
    default User createCreator() {
        return new User();
    }

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

    @IterableMapping(elementTargetType = AnnouncementDto.class)
    List<AnnouncementDto> toDtoList(List<Announcement> announcementList);

    @Mapping(target = "creator", source = "creator", qualifiedByName = "createCreatorDtoFromUser")
    @Mapping(target = "classesId", source = "classes", qualifiedByName = "idFromClasses")
    AnnouncementDto toDto(Announcement announcement);

    @Named("createCreatorDtoFromUser")
    static CreatorDto createCreatorDtoFromUser(User user) {
        return UserMapper.INSTANCE.toCreatorDto(user);
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
