package ru.oks.spring.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.oks.spring.jdbc.entity.User;
import ru.oks.spring.mvc.service.UserService;

/**
 * Сервис UserService для Spring Security.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userService.getUser(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
}