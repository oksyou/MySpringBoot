package ru.oks.spring.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.JPA.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    public Document findByDocName(String docName);
}
