package ru.oks.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oks.spring.jdbc.entity.User;
import ru.oks.spring.mvc.dto.TokenDto;
import ru.oks.spring.mvc.dto.UserDto;
import ru.oks.spring.mvc.service.UserService;
import ru.oks.spring.security.jwt.JwtProvider;

/**
 * REST контроллер для работы с пользователями.
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
    @GetMapping(value = "/{login}")
    public UserDto getUser(@PathVariable String login) {
        return new UserDto(login, null);
    }

    /**
     * Обновление пароля.
     *
     * @param login   логин
     * @param userDTO дто с новым паролем
     */
    @PatchMapping(value = "/{login}")
    public void updatePassword(@PathVariable String login, @RequestBody UserDto userDTO) {
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
    @PostMapping("/registration")
    public void addUser(@RequestBody UserDto userDTO) {
        userService.addUser(userDTO);
    }

    /**
     * Аутентификация.
     *
     * @param userDTO логин и пароль
     * @return токен
     */
    @PostMapping("/authentication")
    public TokenDto auth(@RequestBody UserDto userDTO) {
        User userEntity = userService.getByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new TokenDto(token);
    }
}
