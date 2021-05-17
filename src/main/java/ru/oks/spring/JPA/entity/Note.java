package ru.oks.spring.JPA.entity;

import ru.oks.spring.JDBC.entity.User;

import javax.persistence.*;

/**
 * Запись.
 */
@Entity
@Table(name = "note")
public class Note {
    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    /**
     * Название.
     */
    @Column(name = "title")
    private String title;
    /**
     * Описание.
     */
    @Column(name = "description")
    private String description;
    /**
     * Принадлежность записи пользователю.
     */
    @ManyToOne
    @JoinColumn(name = "user_login", nullable = false)
    private User userlogin;

    public Note() {
    }

    public Note(String title, String description, User user_login) {
        this.title = title;
        this.description = description;
        this.userlogin = user_login;
    }

    public Note(long id, String title, String description, User userlogin) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userlogin = userlogin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser_login() {
        return userlogin;
    }

    public void setUser_login(User user_login) {
        this.userlogin = user_login;
    }
}
