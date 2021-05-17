package ru.oks.spring.JPA.entity;

import javax.persistence.*;

/**
 * Документ.
 */
@Entity
@Table(name = "document")
public class Document {
    /**
     * Название документа.
     */
    @Id
    @Column(name = "docname")
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
