package ru.dnlkk.ratingusbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Column(name = "organisation_mail")
    @NotNull
    private String organisationMail;

    @Column(name = "organisation_name")
    @NotNull
    private String organisationName;

    @Column(name = "organisation_address")
    @NotNull
    private String organisationAddress;

    @Column(name = "organisation_phone")
    @NotNull
    private String organisationPhone;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @NotNull
    private User creator;
}
