package ru.dnlkk.ratingusbackend.api.dtos.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduleDayDTO {
    private int dayOfWeek;
    private List<ScheduleItemDTO> studies;
}
