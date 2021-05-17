package ru.oks.spring.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.JPA.entity.Note;

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
    List<Note> findAllByUserlogin(User user);

    /**
     * Поиск записи пользователя по идентификатору записи.
     *
     * @param user пользователь.
     * @param id идентификатор записи.
     * @return запись.
     */
    Note findNoteByUserloginAndId(User user, long id);

    /**
     * Удаление записи пользователя по идентификатору записи.
     *
     * @param user польователь.
     * @param id идентификатор записи.
     */
    void deleteNoteByUserloginAndId(User user, long id);
}
