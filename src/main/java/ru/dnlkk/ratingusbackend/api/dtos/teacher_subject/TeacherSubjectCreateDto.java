package ru.dnlkk.ratingusbackend.api.dtos.teacher_subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherSubjectCreateDto {
    private int subjId;
    private int teacherId;
}
