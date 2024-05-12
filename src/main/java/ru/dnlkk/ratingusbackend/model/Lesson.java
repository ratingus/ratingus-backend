package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lesson_number")
    private int lessonNumber;

    @Column(name = "homework")
    private String homework;

    @Column(name = "theme")
    private String theme;

    @Column(name = "date_of_lesson")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @OneToMany(mappedBy = "lesson")
    private List<Diary> diaries;
}
