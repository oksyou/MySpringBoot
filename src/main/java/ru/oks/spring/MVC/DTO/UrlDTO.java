package ru.oks.spring.MVC.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * ДТО с длинной и короткой ссылками.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlDTO {
    /**
     * Короткий идентификатор.
     */
    private long id;
    /**
     * Длинная ссылка.
     */
    private String longUrl;

    public UrlDTO() {
    }

    public UrlDTO(long id, String longUrl) {
        this.id = id;
        this.longUrl = longUrl;
    }
}
