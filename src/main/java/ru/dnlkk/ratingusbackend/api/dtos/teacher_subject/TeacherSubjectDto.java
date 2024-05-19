package ru.dnlkk.ratingusbackend.api.dtos.teacher_subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.subject.SubjectDto;
import ru.dnlkk.ratingusbackend.api.dtos.user_role.UserRoleSimpleDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherSubjectDto {
    private int id;
    private SubjectDto subject;
    private UserRoleSimpleDto teacher;
}
