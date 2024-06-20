package ru.dnlkk.ratingusbackend.api.dtos.magazine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.model.enums.Attendance;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GradeDto {
    private Integer scheduleId;
    private Integer studentId;
    private Timestamp date;
    private Integer lessonId; // Может быть null
    private Integer lessonStudentId; // Может быть null
    private String mark;
    private Attendance attendance;
}
