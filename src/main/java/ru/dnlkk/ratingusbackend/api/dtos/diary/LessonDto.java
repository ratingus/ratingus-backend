package ru.dnlkk.ratingusbackend.api.dtos.diary;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.TeacherDto;
import ru.dnlkk.ratingusbackend.model.enums.Attendance;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LessonDto {
    private Integer scheduleId;
    private Integer lessonId;
    private Integer studentLessonId;
    private Integer teacherSubjectId;
    private String subject;
    private TeacherDto teacher;
    private Integer timetableNumber;
    private String mark;
    private Attendance attendance;
    private String homework;

    @Size(min = 0, max = 1000)
    private String note;
    private Timestamp startTime;
    private Timestamp endTime;
}
