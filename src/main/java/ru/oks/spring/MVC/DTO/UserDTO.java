package ru.oks.spring.MVC.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * ДТО пользователя.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    /**
     * Логин.
     */
    private String login;
    /**
     * Пароль.
     */
    private String password;

    public UserDTO() {
    }

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
