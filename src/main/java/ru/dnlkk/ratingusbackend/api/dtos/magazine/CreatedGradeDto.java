package ru.dnlkk.ratingusbackend.api.dtos.magazine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreatedGradeDto {
    private int studentLessonId;
    private int lessonId;
}
