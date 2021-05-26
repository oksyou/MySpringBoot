package ru.oks.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.jpa.entity.Document;

/**
 * Репозиторий JPA для работы с документами.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    /**
     * Найти документ по названию документа.
     *
     * @param docName название документа.
     * @return документ.
     */
    Document findByDocName(String docName);
}
