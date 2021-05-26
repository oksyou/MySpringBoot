package ru.oks.spring.JPA.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Документ на ПК.
 */
@Getter
@Setter
@Entity
@Table(name = "local_document")
public class LocalDocument {
    /**
     * Название документа.
     */
    @Id
    @Column(name = "local_doc_name")
    private String docName;

    public LocalDocument(String docName) {
        this.docName = docName;
    }

    public LocalDocument() {
    }

}
