package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findUserRoleByUser(User user);

    List<UserRole> findUserRolesBySchoolIdAndRole(int schoolId, Role role);
    UserRole findUserRoleByUserAndSchool(User user, School school);
}
