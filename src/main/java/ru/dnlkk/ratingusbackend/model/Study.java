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
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "studies")
    private List<User> teachers;

    @OneToMany(mappedBy = "study")
    private List<Lesson> lessons;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
}
