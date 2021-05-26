package ru.oks.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oks.spring.jdbc.entity.User;
import ru.oks.spring.jdbc.repository.UserRepository;
import ru.oks.spring.jpa.entity.Note;
import ru.oks.spring.jpa.repository.NoteRepository;
import ru.oks.spring.mvc.dto.NoteDto;
import ru.oks.spring.mvc.dto.UserDto;

import java.util.List;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Получить пользователя.
     *
     * @param login логин
     * @return пользователь
     */
    public User getUser(String login) {
        return userRepository.getUserByLogin(login);
    }

    /**
     * Добавить пользователя.
     *
     * @param userDTO логин и пароль
     */
    public void addUser(UserDto userDTO) {
        if (getUser(userDTO.getLogin()) == null)
            userRepository.insert(new User(userDTO.getLogin(), passwordEncoder.encode(userDTO.getPassword())));
    }

    /**
     * Обновить пароль.
     *
     * @param login    логин
     * @param password пароль
     */
    public void updatePassword(String login, String password) {
        userRepository.update(login, passwordEncoder.encode(password));
    }

    /**
     * Удалить пользователя.
     *
     * @param login логин
     */
    public void deleteUser(String login) {
        userRepository.delete(login);
    }

    /**
     * Получить по логину и паролю.
     *
     * @param login    логин
     * @param password пароль
     * @return пользователь
     */
    public User getByLoginAndPassword(String login, String password) {
        User user = getUser(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
//работа с записями пользователя

    /**
     * Добавление новой записи.
     *
     * @param login   логин
     * @param noteDTO запись
     */
    public void addNoteForUser(String login, NoteDto noteDTO) {
        noteRepository.save(new Note(noteDTO.getTitle(), noteDTO.getDescription(), getUser(login)));
    }

    /**
     * Получение всех записей.
     *
     * @param login логин
     * @return записи
     */
    public List<Note> getAllNotesByUser(String login) {
        return noteRepository.findAllByUserLogin(getUser(login));
    }

    /**
     * Обновление записи.
     *
     * @param login   логин
     * @param id      идентификатор записи
     * @param noteDTO запись
     */
    public void updateNote(String login, long id, NoteDto noteDTO) {
        noteRepository.save(new Note(id, noteDTO.getTitle(), noteDTO.getDescription(), getUser(login)));
    }

    /**
     * Получение записи.
     *
     * @param login логин
     * @param id    идентификатор записи
     * @return запись
     */
    public Note getNoteForUserById(String login, long id) {
        return noteRepository.findNoteByUserLoginAndId(getUser(login), id);
    }

    /**
     * Удаление записи.
     *
     * @param login логин
     * @param id    идентификатор записи
     */
    @Transactional
    public void deleteNoteForUserById(String login, long id) {
        noteRepository.deleteNoteByUserLoginAndId(getUser(login), id);
    }

}
