package ru.dnlkk.ratingusbackend.api.dtos.announcement;

import lombok.*;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.api.dtos.user.CreatorDto;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnnouncementDto {
    private int id;
    private String name;
    private String content;
    private CreatorDto creator;
    private Timestamp createDate;
    private int views;
    private List<ClassDto> classes;
}
