package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.model.enums.Attendance;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MarkDto {
    private Integer studentLessonId;
    private Integer lessonId;
    private String mark;
    private Attendance attendance;
}
