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
@Table(name = "school")
public class School implements IdGettable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "school")
    private List<Timetable> timetables;

    @OneToMany(mappedBy = "school")
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "school")
    private List<Class> classes;

    @OneToMany(mappedBy = "school")
    private List<UserCode> userCodes;

    @OneToMany(mappedBy = "school")
    private List<Subject> subjects;
}
