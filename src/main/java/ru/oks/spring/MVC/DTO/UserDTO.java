package ru.oks.spring.MVC.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ДТО пользователя.
 */
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
