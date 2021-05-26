package ru.oks.spring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Запись пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    /**
     * Название.
     */
    private String title;
    /**
     * Содержание записи.
     */
    private String description;
}
