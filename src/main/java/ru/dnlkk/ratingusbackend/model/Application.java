package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dnlkk.ratingusbackend.api.dtos.application.ApplicationStatusType;
import ru.dnlkk.ratingusbackend.model.helper_classes.IdGettable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class Application implements IdGettable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "phone")
    @NotNull
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatusType status;

    @OneToOne
    private UserCode code;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @NotNull
    private User creator;
}
