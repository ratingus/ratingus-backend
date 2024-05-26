package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dnlkk.ratingusbackend.api.model.UserDto;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.repository.SchoolRepository;
import ru.dnlkk.ratingusbackend.repository.UserCodeRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;
import ru.dnlkk.ratingusbackend.repository.UserRoleRepository;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        if (registerUser.isEmpty()) {
            return userRepository.save(user);
        }
        throw new RuntimeException("User already exists");
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
        String[] loginSplit = login.split("-");
        String username = loginSplit[0];
        String schoolId = loginSplit.length > 1 ? loginSplit[1] : "null";
        User user = getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        UserRole userRole;
        if (!schoolId.isBlank() && !schoolId.equals("null")) {
            // TODO: пока норм, но только если у пользователя не более одной роли в школе
            School school = schoolRepository.findSchoolById(Integer.parseInt(schoolId));
            userRole = userRoleRepository.findUserRoleByUserAndSchool(user, school);
        } else {
            List<UserRole> userRoles = userRoleRepository.findAllByUser(user);
            userRole = userRoles.isEmpty() ? null : userRoles.stream().max(Comparator.comparing(UserRole::getLastLogin)).get();
        }
        return new UserDetailsImpl(user, userRole);
    }

    public void saveUserRole(UserRole userRole) {
        if (userRole != null) {
            userRole.setLastLogin(new Timestamp(System.currentTimeMillis()));
            userRoleRepository.save(userRole);
        }
    }

}