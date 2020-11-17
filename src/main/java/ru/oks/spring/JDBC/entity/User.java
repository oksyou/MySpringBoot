package ru.oks.spring.JDBC.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.oks.spring.JPA.entity.Note;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
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
