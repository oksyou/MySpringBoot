package ru.oks.spring.MVC.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Запись пользователя.
 */
@Getter
@Setter
public class NoteDTO {
    /**
     * Название.
     */
    private String title;
    /**
     * Содержание записи.
     */
    private String description;

    public NoteDTO() {

    }

    public NoteDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
