package ru.oks.spring.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.JPA.entity.LocalDocument;

/**
 * Репозиторий JPA для работы с локальными документами.
 */
@Repository
public interface LocalDocumentRepository extends JpaRepository<LocalDocument, String> {
}
