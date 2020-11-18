package ru.oks.spring.JPA.entity;

import javax.persistence.*;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @Column(name = "docname")
    private String docName;

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
