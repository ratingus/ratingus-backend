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
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "roleClass")
    private List<UserRole> userRoleList;
  
    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    @ManyToMany(mappedBy = "classes")
    private List<User> students;

    @ManyToMany(mappedBy = "classes")
    private List<Announcement> announcements;

}
