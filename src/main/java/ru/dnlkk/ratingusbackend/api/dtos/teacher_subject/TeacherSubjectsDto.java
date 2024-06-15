package ru.dnlkk.ratingusbackend.api.dtos.teacher_subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherSubjectsDto {
    private SubjectDto subject;
    private List<TeacherWithSubjectIdDto> teachers;
}
