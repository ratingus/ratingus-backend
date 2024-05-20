package ru.dnlkk.ratingusbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule implements IdGettable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "timetable_id", referencedColumnName = "id")
    private Timetable timetable;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class scheduleForClass;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private TeacherSubject subject;

    @OneToMany(mappedBy = "schedule")
    private List<Lesson> lessons;
}
