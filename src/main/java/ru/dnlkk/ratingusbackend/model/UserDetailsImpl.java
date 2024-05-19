package ru.dnlkk.ratingusbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private final User user;
    private UserRole userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = String.valueOf(Role.GUEST);
        if (userRole != null){
            roleName = userRole.getRole().name();
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
