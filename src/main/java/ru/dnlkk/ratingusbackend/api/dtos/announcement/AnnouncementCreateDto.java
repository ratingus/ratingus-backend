package ru.dnlkk.ratingusbackend.api.dtos.announcement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnnouncementCreateDto {
    private int id;
    private String name;
    private String content;
    private int views;
    private List<ClassDto> classes;
}
