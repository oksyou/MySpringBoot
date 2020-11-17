package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oks.spring.JPA.entity.Note;
import ru.oks.spring.MVC.DTO.NoteDTO;
import ru.oks.spring.MVC.service.UserService;
import ru.oks.spring.security.jwt.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping(value = "/note")
public class NoteController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/add")
    public void addNoteForUser(HttpServletRequest request, @RequestBody NoteDTO noteDTO){
        String token=tokenFromRequest(request);
        if (token!=null)
        userService.addNoteForUser(jwtProvider.getLoginFromToken(token), noteDTO);
    }

    @PatchMapping("/{id}")
    public void updateNodeForUser(HttpServletRequest request, @PathVariable long id, @RequestBody NoteDTO noteDTO){
        String token=tokenFromRequest(request);
        if (token!=null)
        userService.updateNote(jwtProvider.getLoginFromToken(token), id, noteDTO);
    }

    @GetMapping("/{id}")
    public NoteDTO getNoteForUserById(HttpServletRequest request, @PathVariable long id){
        String token=tokenFromRequest(request);
        Note note=userService.getNoteForUserById(jwtProvider.getLoginFromToken(token), id);
        return new NoteDTO(note.getTitle(), note.getDescription());
    }

    @GetMapping("/getall")
    public List<NoteDTO> getAllNoteForUser(HttpServletRequest request){
        String token=tokenFromRequest(request);

        List<Note> notes= userService.getAllNotesByUser(jwtProvider.getLoginFromToken(token));
        List<NoteDTO> noteDTOList=new ArrayList<>();
        for (Note n:notes){
            noteDTOList.add(new NoteDTO(n.getTitle(), n.getDescription()));
        }
        return noteDTOList;
    }

    @DeleteMapping("/{id}")
    public void deleteNoteForUserById(HttpServletRequest request, @PathVariable long id){
        String token=tokenFromRequest(request);
        userService.deleteNoteForUserByid(jwtProvider.getLoginFromToken(token), id);
    }

    private String tokenFromRequest(HttpServletRequest request){
        String token=request.getHeader("Authorization");
        if (hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
