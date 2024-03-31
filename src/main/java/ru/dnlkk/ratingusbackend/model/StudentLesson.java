package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.model.enums.Attendance;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students_lessons")
public class StudentLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "mark")
    private String mark;

    @Column(name = "note")
    private String note;

    @Column(name = "attendance")
    private Attendance attendance;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private  User student;
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;


}