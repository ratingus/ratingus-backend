package ru.dnlkk.ratingusbackend.api.dtos.schedule;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MergeSubjectWithTeacherRequestDTO {
    private int subjectId;
    private int teacherId;
}
