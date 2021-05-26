package ru.oks.spring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.oks.spring.jdbc.entity.User;
import ru.oks.spring.jdbc.repository.UserRepository;
import ru.oks.spring.mvc.service.UserService;

import static org.mockito.Mockito.when;

/**
 * Тестирование UserService.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    /**
     * Тестирование получения пользователя с помощью логина и пароля.
     */
    @Test
    public void testGetByLoginAndPassword() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = new User("login", "password");
        when(passwordEncoder.matches(user.getPassword(), "password")).thenReturn(true);
        when(userRepository.getUserByLogin("login")).thenReturn(user);
        User user1 = userService.getByLoginAndPassword("login", "password");
        Assertions.assertEquals(user.getLogin(), user1.getLogin());
        Assertions.assertEquals(user.getPassword(), user1.getPassword());

        when(userRepository.getUserByLogin("loginNull")).thenReturn(new User());
        User userNull = userService.getByLoginAndPassword("loginNull", "password");
        Assertions.assertNull(userNull);
    }

}
