package ru.oks.spring.MVC.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Ip адрес, язык, тело запроса.
 */
@Getter
@Setter
public class IpLanguageBodyDTO {
    /**
     * Ip адрес запроса.
     */
    private String ip;
    /**
     * Язык запроса.
     */
    private String lang;
    /**
     * Тело запроса.
     */
    private String body;

}
