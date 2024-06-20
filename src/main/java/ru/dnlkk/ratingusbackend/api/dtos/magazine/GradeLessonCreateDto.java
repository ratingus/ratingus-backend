package ru.dnlkk.ratingusbackend.api.dtos.magazine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GradeLessonCreateDto {
    private Integer scheduleId;
    private Integer lessonId;
    private Integer studentId;
    private Integer lessonStudentId;
}
