package ru.oks.spring.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.JPA.entity.Url;

/**
 * Репозиторий JPA для работы с url.
 */
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url getByLongUrl(String longUrl);
}
