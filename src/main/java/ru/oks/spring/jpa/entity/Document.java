package ru.oks.spring.jpa.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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

    public Document(String docName) {
        this.docName = docName;
    }
}
