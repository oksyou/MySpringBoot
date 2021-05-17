package ru.oks.spring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.JDBC.repository.UserRepository;
import ru.oks.spring.JPA.repository.NoteRepository;
import ru.oks.spring.MVC.DTO.UserDTO;
import ru.oks.spring.MVC.controller.UserController;
import ru.oks.spring.MVC.service.UserService;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Тестирование UserService.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    NoteRepository noteRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    /**
     * Тестирование получения пользователя с помощью логина и пароля.
     */
    @Test
    public void testGetByLoginAndPassword(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user=new User("login", "password");
        when(passwordEncoder.matches(user.getPassword(), "password")).thenReturn(true);
        when(userRepository.getUserByLogin("login")).thenReturn(user);
        User user1=userService.getByLoginAndPassword("login", "password");
        Assertions.assertEquals(user.getLogin(), user1.getLogin());
        Assertions.assertEquals(user.getPassword(), user1.getPassword());

        when(userRepository.getUserByLogin("loginNull")).thenReturn(new User());
        User userNull=userService.getByLoginAndPassword("loginNull", "password");
        Assertions.assertNull(userNull);
    }

}