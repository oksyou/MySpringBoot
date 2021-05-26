package ru.oks.spring.JPA.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность для хранения url.
 */
@Getter
@Setter
@Entity
@Table
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String longUrl;

    public Url() {
    }

    public Url(String longUrl) {
        this.longUrl = longUrl;
    }
}
