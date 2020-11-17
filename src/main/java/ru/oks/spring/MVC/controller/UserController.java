package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.JPA.entity.Note;
import ru.oks.spring.MVC.DTO.NoteDTO;
import ru.oks.spring.MVC.DTO.TokenDTO;
import ru.oks.spring.MVC.DTO.UserDTO;
import ru.oks.spring.MVC.service.UserService;
import ru.oks.spring.security.jwt.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping(value = "/{login}", produces = "application/json")
    public UserDTO getUser(@PathVariable String login){
        return new UserDTO(login, null);
    }

    @PatchMapping(value = "/{login}", produces = "application/json")
    public void updatePassword(@PathVariable String login, @RequestBody UserDTO userDTO) {
        userService.updatePassword(login, userDTO.getPassword());
    }

    @DeleteMapping("/{login}")
    public void deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
    }

    //регистрация
    @PostMapping("/reg")
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    //авторизация
    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody UserDTO userDTO) {
        User userEntity = userService.getByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new TokenDTO(token);
    }




}
