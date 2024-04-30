package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.api.dtos.UserSchoolDto;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return null;
//        return repository.save(user);
    }
    public User create(User user) {
//        if (repository.existsByUsername(user.getName())) {
//            throw new RuntimeException("Пользователь с таким именем уже существует");
//        }
//
//        if (repository.existsByLogin(user.getLogin())) {
//            throw new RuntimeException("Пользователь с таким login уже существует");
//        }

        return null;
//        return save(user);
    }

    public User getUserbyId(int userId){
        return repository.getUserById(userId);
    }

    public UserSchoolDto getAllSchoolPagination(int userId) {
        Optional<UserSchoolDto> optionalSchools = repository.findAllSchoolsByUserId(userId);
        return optionalSchools.orElse(null);
    }

    public void updateUser(UserDto userDto, int userId){
        repository.findById(userId)
                .map(user -> {
                    user.setName(userDto.getName());
                    user.setSurname(userDto.getSurname());
                    user.setPatronymic(userDto.getPatronymic());
                    user.setBirthDate(userDto.getBirthDate());
                    return user;
                })
                .orElse(null);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getByUsername(String username) {
        return null;
//        return repository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }


}