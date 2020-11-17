package ru.oks.spring.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.JDBC.repository.UserRepository;
import ru.oks.spring.JPA.entity.Note;
import ru.oks.spring.JPA.repository.NoteRepository;
import ru.oks.spring.MVC.DTO.NoteDTO;
import ru.oks.spring.MVC.DTO.UserDTO;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;

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


    public void addNoteForUser(String login, NoteDTO noteDTO){
        noteRepository.save(new Note(noteDTO.getTitle(), noteDTO.getDescription(), getUser(login)));
    }

//    public Note getNoteByUserLoginAndTitle(String login, String title){
//        return noteRepository.findNoteByUserloginAndTitle(getUser(login), title);
//    }

    public List<Note> getAllNotesByUser(String login){
        return noteRepository.findAllByUserlogin(getUser(login));
    }

    public void updateNote(String login, long id, NoteDTO noteDTO){
        noteRepository.save(new Note(id, noteDTO.getTitle(), noteDTO.getDescription(), getUser(login)));
    }
    public Note getNoteForUserById(String login, long id){
        return noteRepository.findNoteByUserloginAndId(getUser(login), id);
    }
    @Transactional
    public void deleteNoteForUserByid(String login, long id){
        noteRepository.deleteNoteByUserloginAndId(getUser(login), id);
    }

}
