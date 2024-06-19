package ru.dnlkk.ratingusbackend.api.dtos.school;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.timetable.TimetableDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SchoolProfileDto {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int totalStudents;
    private int totalTeachers;
    private List<TimetableDto> timetable;
}
