package ru.oks.spring.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.oks.spring.jdbc.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Сущность для хранния записи.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Note {
    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    /**
     * Название.
     */
    @Column
    private String title;
    /**
     * Описание.
     */
    @Column
    private String description;
    /**
     * Принадлежность записи пользователю.
     */
    @ManyToOne
    @JoinColumn(name = "user_login", nullable = false)
    private User userLogin;

    public Note(String title, String description, User userLogin) {
        this.title = title;
        this.description = description;
        this.userLogin = userLogin;
    }
}
