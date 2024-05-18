package ru.dnlkk.ratingusbackend.api.dtos.announcement;

import lombok.*;
import ru.dnlkk.ratingusbackend.api.dtos.user.CreatorDto;
import ru.dnlkk.ratingusbackend.model.User;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnnouncementDto {
    private int id;
    private String name;
    private String content;
//    private int creatorId;
    private CreatorDto creator;
    private Timestamp createDate;
    private int views;
    private List<Integer> classesId;
}
