package ru.dnlkk.ratingusbackend.api.dtos.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DayLessonsDto {
    private Integer dayOfWeek;
    private Timestamp dateTime;
    private List<LessonDto> studies;
}
