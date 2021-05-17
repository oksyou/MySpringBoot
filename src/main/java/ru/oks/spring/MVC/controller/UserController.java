package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.MVC.DTO.TokenDTO;
import ru.oks.spring.MVC.DTO.UserDTO;
import ru.oks.spring.MVC.service.UserService;
import ru.oks.spring.security.jwt.JwtProvider;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Получение пользователя по логину.
     *
     * @param login логин
     * @return дто пользователя
     */
    @GetMapping(value = "/{login}", produces = "application/json")
    public UserDTO getUser(@PathVariable String login) {
        return new UserDTO(login, null);
    }

    /**
     * Обновление пароля.
     *
     * @param login логин
     * @param userDTO дто с новым паролем
     */
    @PatchMapping(value = "/{login}", produces = "application/json")
    public void updatePassword(@PathVariable String login, @RequestBody UserDTO userDTO) {
        userService.updatePassword(login, userDTO.getPassword());
    }

    /**
     * Удаление пользователя.
     *
     * @param login логин
     */
    @DeleteMapping("/{login}")
    public void deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
    }

    /**
     * Регистрация нового пользователя.
     *
     * @param userDTO логин и пароль
     */
    @PostMapping("/reg")
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    /**
     * Аутентификация.
     *
     * @param userDTO логин и пароль
     * @return токен
     */
    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody UserDTO userDTO) {
        User userEntity = userService.getByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new TokenDTO(token);
    }
}
