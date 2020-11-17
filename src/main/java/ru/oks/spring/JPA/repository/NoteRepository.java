package ru.oks.spring.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.spring.JDBC.entity.User;
import ru.oks.spring.JPA.entity.Note;
import ru.oks.spring.MVC.DTO.NoteDTO;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    //public int countNotesById(long id);
//    public List<Note> findAllBy
//    public List<Note> findAllByUser(User user);
    //public Note findNoteByUserloginAndTitle(User user, String title);
    public List<Note> findAllByUserlogin(User user);
    public Note findNoteByUserloginAndId(User user, long id);
    public void deleteNoteByUserloginAndId(User user, long id);
}
