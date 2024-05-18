package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "announcement")
public class Announcement implements IdGettable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @Column(name = "create_date")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "views")
    private int views;

    @ManyToMany
    @JoinTable(
            name = "class_announcement",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<Class> classes;
}
