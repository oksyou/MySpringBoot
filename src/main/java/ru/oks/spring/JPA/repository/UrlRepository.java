package ru.oks.spring.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.JPA.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    public Url getByLongUrl(String longUrl);
}
