package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson implements IdGettable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "homework")
    private String homework;

    @Column(name = "theme")
    private String theme;

    @Column(name = "finished")
    private Boolean finished;

    @Column(name = "date_of_lesson")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    @OneToMany(mappedBy = "lesson")
    private List<StudentLesson> studentLessons;
}
