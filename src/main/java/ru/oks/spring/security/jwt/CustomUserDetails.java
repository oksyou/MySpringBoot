package ru.oks.spring.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.oks.spring.jdbc.entity.User;

import java.util.Collection;

/**
 * Класс User для Spring Security.
 */
public class CustomUserDetails implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    /**
     * Конвертация из сущности User в сущность, пригодную для Spring Security.
     *
     * @param userEntity сущность пользователя
     * @return конвертированная сущность пользователя
     */
    public static CustomUserDetails fromUserEntityToCustomUserDetails(User userEntity) {
        CustomUserDetails c = new CustomUserDetails();
        c.login = userEntity.getLogin();
        c.password = userEntity.getPassword();

        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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