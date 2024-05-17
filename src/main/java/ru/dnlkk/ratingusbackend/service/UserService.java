package ru.dnlkk.ratingusbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dnlkk.ratingusbackend.api.model.UserDto;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserCode;
import ru.dnlkk.ratingusbackend.model.UserRole;
import ru.dnlkk.ratingusbackend.repository.UserCodeRepository;
import ru.dnlkk.ratingusbackend.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private UserCodeRepository userCodeRepository;

    //TODO: исправить регистрацию и вход

    public User getUserById(UserDto userDto){
        return userRepository.findUserById(userDto.getId());
    }

    public User registerUser(User user) {
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            return null;
        }
        return userRepository.save(user);
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
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getByUsername(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", login)
        ));

        String roleName = user.getUserRole().getName();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }

}