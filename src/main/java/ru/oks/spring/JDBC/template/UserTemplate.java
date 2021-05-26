package ru.oks.spring.JDBC.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.JDBC.repository.UserRepository;

/**
 * Шаблон реализации репозитория пользователя.
 */
@Component
public class UserTemplate implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    /**
     * Вставка.
     *
     * @param user пользователь.
     */
    @Override
    public void insert(User user) {
        jdbcTemplateObject.update(
                "insert into public.users (login, password) values(?,?)", user.getLogin(), user.getPassword());
    }

    /**
     * Получение пользователя по логину.
     *
     * @param login логин.
     * @return пользователь.
     */
    @Override
    public User getUserByLogin(String login) {
        String getSql = "SELECT password FROM public.users WHERE login=?";
        try {
            String password = jdbcTemplateObject.queryForObject(getSql, new Object[]{login}, String.class);
            return new User(login, password);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Удаление пользователя по логину.
     *
     * @param login логин.
     */
    @Override
    public void delete(String login) {
        String deleteSql = "DELETE FROM public.users WHERE login=?";
        jdbcTemplateObject.update(deleteSql, login);
    }

    /**
     * Обновление пароля пользователя по логину.
     *
     * @param login    логин.
     * @param password пароль.
     */
    @Override
    public void update(String login, String password) {
        String updateSql = "UPDATE public.users SET password=? WHERE login=?";
        jdbcTemplateObject.update(updateSql, password, login);
    }
}
