package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LessonDto {
    private int id;
    private String homework;
    private String theme;
    private Boolean finished;
    private Timestamp date;
    private Integer lessonNumber;
}
