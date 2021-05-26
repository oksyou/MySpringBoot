package ru.oks.spring.jdbc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.oks.spring.jpa.entity.Note;

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
@NoArgsConstructor
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



    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
