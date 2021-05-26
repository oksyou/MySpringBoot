package ru.oks.spring.JPA.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Документ.
 */
@Getter
@Setter
@Entity
@Table
public class Document {
    /**
     * Название документа.
     */
    @Id
    @Column
    private String docName;
    /**
     * Документ как массив байтов.
     */
    @Column
    @Lob
    private byte[] file;

    public Document() {
    }

    public Document(String docName) {
        this.docName = docName;
    }
}
