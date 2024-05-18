package ru.dnlkk.ratingusbackend.api.dtos.timetable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TimetableDto {
    private int id;
    private int lessonNumber;
    private Timestamp startTime;
    private Timestamp endTime;
}
