package ru.oks.spring.mvc.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Ip адрес, язык, тело запроса.
 */
@Getter
@Setter
public class IpLanguageBodyDto {
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
