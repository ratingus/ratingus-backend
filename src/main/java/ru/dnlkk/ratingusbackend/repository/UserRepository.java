
package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(int id);
    User findByName(String name);
    boolean existsByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.surname = :surname AND u.patronymic = :patronymic")
    User findByFullName(@Param("name") String name, @Param("surname") String surname, @Param("patronymic") String patronymic);
}
