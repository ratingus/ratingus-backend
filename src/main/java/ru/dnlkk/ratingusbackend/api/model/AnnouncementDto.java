package ru.dnlkk.ratingusbackend.api.model;

import jakarta.persistence.*;
import lombok.*;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.List;

@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
//@Builder
@Data
public class AnnouncementDto {
    private int id;
    private String name;
    private String content;
    private User creator;
    private List<Class> classes;
}
