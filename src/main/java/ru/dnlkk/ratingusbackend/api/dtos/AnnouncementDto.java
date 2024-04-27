package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnnouncementDto {
    private int id;
    private String name;
    private String content;
    private int userId;
    private List<Integer> classesId;
}
