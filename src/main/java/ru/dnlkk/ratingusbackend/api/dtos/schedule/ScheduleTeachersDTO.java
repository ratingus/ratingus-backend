package ru.dnlkk.ratingusbackend.api.dtos.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.TeacherDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduleTeachersDTO {
    private int id;
    private String name;
    private List<TeacherDto> teachers;
}
