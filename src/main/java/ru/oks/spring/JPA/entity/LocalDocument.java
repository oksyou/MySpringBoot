package ru.oks.spring.JPA.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "local_document")
public class LocalDocument {
    @Id
    @Column(name = "local_doc_name")
    private String docName;

    public LocalDocument(String docName) {
        this.docName = docName;
    }

    public LocalDocument() {
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
