package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MagazineDto {
    private List<StudentDto> students;
   private List<MonthLessonDayDto> monthLessonDays;
}
