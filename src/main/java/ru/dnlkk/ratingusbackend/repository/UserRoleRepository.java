package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findUserRoleByUser(User user);

    List<UserRole> findUserRolesBySchoolIdAndRole(int schoolId, Role role);
    UserRole findUserRoleByUserAndSchool(User user, School school);

    UserRole findUserRoleByUserIdAndSchool(int teacherId, School school);

    List<UserRole> findUserRolesBySchoolIdAndRoleClassId(int id, Integer classId);

    Optional<UserRole> findByUserAndRoleAndSchoolId(User user, Role role, int schoolId);

    Optional<UserRole> findByUserAndRole(User user, Role role);

    List<UserRole> findAllByUser(User user);
}
