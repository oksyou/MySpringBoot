package ru.oks.spring.mvc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ДТО с длинной и короткой ссылками.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlDto {
    /**
     * Короткий идентификатор.
     */
    private long id;
    /**
     * Длинная ссылка.
     */
    private String longUrl;
}
