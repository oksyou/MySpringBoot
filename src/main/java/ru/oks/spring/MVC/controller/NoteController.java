package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oks.spring.JPA.entity.Note;
import ru.oks.spring.MVC.DTO.NoteDTO;
import ru.oks.spring.MVC.service.UserService;
import ru.oks.spring.security.jwt.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

/**
 * Контроллер для работы с записями пользователей.
 */
@RestController
@RequestMapping(value = "/note")
public class NoteController {
    @Autowired
    private UserService userService;
    /**
     * Для токенов.
     */
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Добавление записи пользователю.
     *
     * @param request Http запрос
     * @param noteDTO запись
     */
    @PostMapping("/add")
    public void addNoteForUser(HttpServletRequest request, @RequestBody NoteDTO noteDTO) {
        String token = tokenFromRequest(request);
        if (token != null)
            userService.addNoteForUser(jwtProvider.getLoginFromToken(token), noteDTO);
    }

    /**
     * Обновление записи пользователя.
     *
     * @param request Http запрос
     * @param id      идентификатор записи
     * @param noteDTO новая запись
     */
    @PatchMapping("/{id}")
    public void updateNodeForUser(HttpServletRequest request, @PathVariable long id, @RequestBody NoteDTO noteDTO) {
        String token = tokenFromRequest(request);
        if (token != null)
            userService.updateNote(jwtProvider.getLoginFromToken(token), id, noteDTO);
    }

    /**
     * Получение записи пользователя.
     *
     * @param request Http запрос
     * @param id      идентификатор записи
     * @return запись
     */
    @GetMapping("/get/{id}")
    public NoteDTO getNoteForUserById(HttpServletRequest request, @PathVariable long id) {
        String token = tokenFromRequest(request);
        Note note = userService.getNoteForUserById(jwtProvider.getLoginFromToken(token), id);
        return new NoteDTO(note.getTitle(), note.getDescription());
    }

    /**
     * Получение всех записей пользователя.
     *
     * @param request запрос
     * @return записи пользователя
     */
    @GetMapping("/get/all")
    public List<NoteDTO> getAllNoteForUser(HttpServletRequest request) {
        String token = tokenFromRequest(request);

        List<Note> notes = userService.getAllNotesByUser(jwtProvider.getLoginFromToken(token));
        List<NoteDTO> noteDTOList = new ArrayList<>();
        for (Note n : notes) {
            noteDTOList.add(new NoteDTO(n.getTitle(), n.getDescription()));
        }
        return noteDTOList;
    }

    /**
     * Удаление записи.
     *
     * @param request запрос
     * @param id      идентификатор записи
     */
    @DeleteMapping("/{id}")
    public void deleteNoteForUserById(HttpServletRequest request, @PathVariable long id) {
        String token = tokenFromRequest(request);
        userService.deleteNoteForUserById(jwtProvider.getLoginFromToken(token), id);
    }

    /**
     * Получение корректного токена из запроса
     *
     * @param request запрос
     * @return токен
     */
    private String tokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
