package ru.dnlkk.ratingusbackend.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationDto;
import ru.dnlkk.ratingusbackend.model.Application;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.List;

@Mapper()
public interface ApplicationMapper {
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);
    @Mapping(target = "creator", source = "creatorId", qualifiedByName = "getCreatorById")
    Application toEntity(ApplicationDto applicationDto);
    @IterableMapping(elementTargetType = Application.class)
    List<Application> toEntityList(List<ApplicationDto> applicationDtoList);

    @Named("getCreatorById")
    static User getCreatorById(int userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    @Mapping(target = "creatorId", source = "creator.id")
    ApplicationDto toDto(Application application);
    @IterableMapping(elementTargetType = ApplicationDto.class)
    List<ApplicationDto> toDtoList(List<Application> applicationList);
}
