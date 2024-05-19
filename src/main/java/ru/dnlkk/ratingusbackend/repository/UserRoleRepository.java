package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findUserRoleByUser(User user);

    UserRole findUserRoleByUserAndSchool(User user, School school);
}
