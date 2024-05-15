package ru.dnlkk.ratingusbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lesson_number")
    private int lessonNumber;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class scheduleForClass;

    @ManyToOne
    @JoinColumn(name = "timetable_id", referencedColumnName = "id")
    private Timetable timetable;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;


}
