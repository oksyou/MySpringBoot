package ru.oks.spring.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.JDBC.repository.UserRepository;
import ru.oks.spring.MVC.DTO.UserDTO;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(String login) {
        return userRepository.getUserByLogin(login);
    }

    public void addUser(UserDTO userDTO) {
        if (getUser(userDTO.getLogin()) == null)
            userRepository.insert(new User(userDTO.getLogin(), passwordEncoder.encode(userDTO.getPassword())));
    }

    public void updatePassword(String login, String password) {
        userRepository.update(login, passwordEncoder.encode(password));
    }

    public void deleteUser(String login) {
        userRepository.delete(login);
    }

    public User getByLoginAndPassword(String login, String password) {
        User user = getUser(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
