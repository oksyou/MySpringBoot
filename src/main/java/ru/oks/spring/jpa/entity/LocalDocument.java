package ru.oks.spring.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "local_document")
public class LocalDocument {
    /**
     * Название документа.
     */
    @Id
    @Column(name = "local_doc_name")
    private String docName;
}
