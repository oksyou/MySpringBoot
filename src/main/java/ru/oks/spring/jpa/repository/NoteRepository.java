package ru.oks.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.jdbc.entity.User;
import ru.oks.spring.jpa.entity.Note;

import java.util.List;

/**
 * Репозиторий JPA для работы с записями.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Поиск списка записей пользователя.
     *
     * @param user пользователь.
     * @return список записей.
     */
    List<Note> findAllByUserLogin(User user);

    /**
     * Поиск записи пользователя по идентификатору записи.
     *
     * @param user пользователь.
     * @param id   идентификатор записи.
     * @return запись.
     */
    Note findNoteByUserLoginAndId(User user, long id);

    /**
     * Удаление записи пользователя по идентификатору записи.
     *
     * @param user польователь.
     * @param id   идентификатор записи.
     */
    void deleteNoteByUserLoginAndId(User user, long id);
}
