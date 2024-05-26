package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole implements IdGettable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "creator")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "student")
    private List<StudentLesson> studentsLessons;

    @OneToMany(mappedBy = "user")
    private List<UserCode> usersCodes;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
    // из роли вытаскивать школу для JWT.

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class roleClass;

    @Column(name = "role")
    @Enumerated(EnumType.STRING) //аннотация нужна, чтобы Enum конвертировать в правильный тип
    private Role role;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherSubject> teacherSubjects;

    @Column(name = "last_login")
    private Timestamp lastLogin;
}
