package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.Data;

import java.util.List;

@Data
public class WeekLessonDto {

    private int weekNumber;

    private List<DayLessonDto> week;
}
