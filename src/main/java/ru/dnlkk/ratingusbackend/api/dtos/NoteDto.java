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
public class NoteDto {
    private Integer scheduleId;
    private Timestamp date;
    private Integer lessonId; // Может быть null
    private Integer lessonStudentId; // Может быть null
    private String text;
}
