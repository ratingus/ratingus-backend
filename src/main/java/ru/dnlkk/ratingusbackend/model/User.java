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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @OneToMany(mappedBy = "user")
    private List<UserRole> usersRoles;

    @OneToMany(mappedBy = "creator")
    private List<Application> applications;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToMany(mappedBy = "creator")
    private List<UserCode> usersCodes;
}
