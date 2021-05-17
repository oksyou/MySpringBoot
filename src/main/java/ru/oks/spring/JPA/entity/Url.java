package ru.oks.spring.JPA.entity;

import javax.persistence.*;

/**
 * УРЛ. Воть.
 */
@Entity
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "long_url")
    private String longUrl;

    public Url() {
    }

    public Url(String longUrl) {
        this.longUrl = longUrl;
    }

    public long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }
}
