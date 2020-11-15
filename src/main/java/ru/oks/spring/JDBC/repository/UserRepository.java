package ru.oks.spring.JDBC.repository;

import org.springframework.stereotype.Repository;
import ru.oks.spring.JDBC.entity.User;


@Repository
public interface UserRepository {

    void insert(User user);

    User getUserByLogin(String login);

    void delete(String login);

    void update(String login, String password);
}
