package ru.oks.spring.MVC.DTO;

/**
 * Запись пользователя.
 */
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
