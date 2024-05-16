package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements IdGettable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter() //todo: не нужно
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

    @OneToOne(mappedBy = "user")
    private UserRole userRole;

    @OneToMany(mappedBy = "creator")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "student")
    private List<StudentLesson> studentsLessons;

    @OneToMany(mappedBy = "user")
    private List<UserCode> usersCodes;

    @OneToMany(mappedBy = "creator")
    private List<Application> applicationsl;

    @ManyToMany
    @JoinTable(
            name = "classes_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<Class> classes;

    // мени ту ван (у каждого юзера одна роль, у роли много юзеров)  /swagger-ui/index.html

    @OneToMany(mappedBy = "creator")
    private List<Application> applications;

    @OneToMany(mappedBy = "creator")
    private List<Application> applications;

    @ManyToMany
    @JoinTable(
            name = "subjects_teachers",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getUserRole().getName());
//        return Collections.singletonList(authority);
//    }
//
//    @Override
//    public String getUsername() {
//        return getLogin();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}