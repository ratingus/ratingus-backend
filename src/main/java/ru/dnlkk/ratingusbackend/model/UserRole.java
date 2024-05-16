package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "userRole")
    private User user;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
    // из роли вытаскивать школу для JWT.

    @Column(name = "role")
    private Role role;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class classForUserRole;
}
