package ru.oks.spring.JDBC.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.oks.spring.JPA.entity.Note;

import javax.persistence.*;
import java.util.List;

/**
 *Сущность пользователя.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Логин пользователя.
     */
    @Id
    @Column(name = "login")
    private String login;
    /**
     * Пароль пользователя.
     */
    @Column(name = "password")
    private String password;

    /**
     * Список записей пользователя.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "userlogin")
    private List<Note> notes;

    public User() {
    }

    public User(String login, String password) {
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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
