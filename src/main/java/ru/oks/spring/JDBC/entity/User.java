package ru.oks.spring.JDBC.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.oks.spring.JPA.entity.Note;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Сущность пользователя.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    /**
     * Логин пользователя.
     */
    @Id
    @Column
    private String login;
    /**
     * Пароль пользователя.
     */
    @Column
    private String password;

    /**
     * Список записей пользователя.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "userLogin")
    private List<Note> notes;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
