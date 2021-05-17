package ru.oks.spring.MVC.DTO;

/**
 * Токен для аутентификации.
 */
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
