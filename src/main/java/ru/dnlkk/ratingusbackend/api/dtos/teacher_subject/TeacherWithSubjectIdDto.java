package ru.dnlkk.ratingusbackend.api.dtos.teacher_subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherWithSubjectIdDto {
    private Integer teacherSubjectId;
    private int id;
    private String name;
    private String surname;
    private String patronymic;
}