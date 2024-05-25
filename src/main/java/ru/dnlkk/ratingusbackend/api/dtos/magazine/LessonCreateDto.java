package ru.dnlkk.ratingusbackend.api.dtos.magazine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LessonCreateDto {
    private Integer scheduleId;
    private Timestamp date;
    private String theme;
    private String homework;
    private boolean finished;
}
