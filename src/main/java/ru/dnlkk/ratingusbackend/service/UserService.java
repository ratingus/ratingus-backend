package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.api.dtos.UserCodeDto;
import ru.dnlkk.ratingusbackend.api.dtos.UserDto;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserCode;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.repository.UserCodeRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private UserCodeRepository userCodeRepository;

    //TODO: исправить регистрацию и вход
//    public User save(UserDto userDto) {
//        return userRepository.save(user);
//    }
//    public User create(User user) {
//        if (userRepository.existsByLogin(user.getLogin())) {
//            throw new RuntimeException("Пользователь с таким login уже существует");
//        }
//        return save(user);
//    }

    public User getUserById(UserDto userDto){
        return userRepository.findUserById(userDto.getId());
    }

    public User updateUser(UserDto updatedUser) {
        User existingUser = userRepository.findUserById(updatedUser.getId());

        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setPatronymic(updatedUser.getPatronymic());
            existingUser.setBirthDate(updatedUser.getBirthDate());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public List<School> getSchoolsByUser(UserDto userDto){
        User user = userRepository.findUserById(userDto.getId());
        if (user != null) {
            List<UserRole> userRoles = user.getUsersRoles();
            return userRoles.stream()
                    .map(UserRole::getSchool)
                    .distinct()
                    .collect(Collectors.toList());
        }
        return null;
    }

    public void addUserToOrganisation(UserCodeDto userCodeDto, UserDetails user){
        UserCode userCode = userCodeRepository.findUserCodesByCode(userCodeDto.getCode());

        UserRole userRole = new UserRole();
        //TODO: посмотреть как расширить и расширить UserDetails для security, чтобы туда(JWT) прокидывать шк, класс и тд.
//        userRole.setUser(user);

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getByUsername(String username) {
        return userRepository.findByName(username);
    }
}