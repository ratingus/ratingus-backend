package ru.dnlkk.ratingusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    List<User> findBySurname(String surname);
//    List<User> findByLogin(String login);
}
