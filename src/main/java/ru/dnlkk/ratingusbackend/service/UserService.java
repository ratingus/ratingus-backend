package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dnlkk.ratingusbackend.api.model.UserDto;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.enums.Role;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;
import ru.dnlkk.ratingusbackend.repository.UserCodeRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;
import ru.dnlkk.ratingusbackend.repository.UserRoleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final UserRoleRepository userRoleRepository;
    private UserCodeRepository userCodeRepository;

    //TODO: исправить регистрацию и вход

    public User getUserById(UserDto userDto){
        return userRepository.findUserById(userDto.getId());
    }

    public User registerUser(User user) {
        var registerUser = userRepository.findByLogin(user.getLogin());
        return registerUser.orElseGet(() -> userRepository.save(user));
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

//    public List<School> getSchoolsByUser(UserDto userDto){
//        User user = userRepository.findUserById(userDto.getId());
//        if (user != null) {
//            List<School> schools = user.getUserRole().getSchool();
//        }
//        return null;
//    }
//
//    public void addUserToOrganisation(UserCodeDto userCodeDto, UserDetails user){
//        UserCode userCode = userCodeRepository.findUserCodesByCode(userCodeDto.getCode());
//
//        UserRole userRole = new UserRole();
//        //TODO: посмотреть как расширить и расширить UserDetails для security, чтобы туда(JWT) прокидывать шк, класс и тд.
////        userRole.setUser(user);
//
//    }

    public Optional<User> getByUsername(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println(login);
        String[] loginSplit = login.split("-");
        String username = loginSplit[0];
        String schoolId = loginSplit.length > 1 ? loginSplit[1] : "null";
        User user = getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        UserRole userRole = null;
        if (!schoolId.isBlank() && schoolId != null && !schoolId.equals("null")) {
            // TODO: пока норм, но только если у пользователя не более одной роли в школе
            School school = schoolRepository.findSchoolById(Integer.parseInt(schoolId));
            userRole = userRoleRepository.findUserRoleByUserAndSchool(user, school);
        }
        return new UserDetailsImpl(user, userRole);
    }

}