package ru.oks.spring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Токен для аутентификации.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    /**
     * Bearer Токен.
     */
    private String token;
}
