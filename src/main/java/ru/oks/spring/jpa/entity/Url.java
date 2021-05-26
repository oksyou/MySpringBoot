package ru.oks.spring.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Entity
@Table
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String longUrl;

    public Url(String longUrl) {
        this.longUrl = longUrl;
    }
}
