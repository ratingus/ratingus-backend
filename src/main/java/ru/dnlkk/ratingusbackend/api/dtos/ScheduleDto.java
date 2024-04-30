package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.Data;
import ru.dnlkk.ratingusbackend.model.Lesson;

import java.util.List;

@Data
public class ScheduleDto {
    private int dayNumber;
    private List<Lesson> lessons;
}
