package ru.oks.spring.MVC.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Токен для аутентификации.
 */
@Getter
@Setter
public class TokenDTO {
    /**
     * Bearer Токен.
     */
    private String token;

    public TokenDTO() {
    }

    public TokenDTO(String token) {
        this.token = token;
    }
}
