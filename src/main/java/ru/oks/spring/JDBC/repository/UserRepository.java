package ru.oks.spring.JDBC.repository;

import org.springframework.stereotype.Repository;
import ru.oks.spring.JDBC.entity.User;

/**
 * Репозиторий для работы с сущностью пользователя.
 */
@Repository
public interface UserRepository {
    /**
     * Вставка.
     *
     * @param user пользователь.
     */
    void insert(User user);

    /**
     * Плучение пользователя по логину.
     *
     * @param login логин.
     * @return пользователь.
     */
    User getUserByLogin(String login);

    /**
     * Удаление пользователя по логину.
     *
     * @param login логин.
     */
    void delete(String login);

    /**
     * Обновление пароля пользователя по логину.
     *
     * @param login логин.
     * @param password пароль.
     */
    void update(String login, String password);

}
