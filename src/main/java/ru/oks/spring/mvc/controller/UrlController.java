package ru.oks.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oks.spring.mvc.dto.UrlDto;
import ru.oks.spring.mvc.service.UrlService;

import java.net.URI;

/**
 * REST контроллер ссылок.
 */
@RestController
@RequestMapping(value = "/url")

public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * Получение короткой ссылки.
     *
     * @param request запрос
     * @return короткая ссылка
     */
    @PostMapping("/convert_hash")
    public UrlDto urlConvertToHash(@RequestBody UrlDto request) {
        return new UrlDto(urlService.convertToShortLink(request), null);
    }

    /**
     * Перенаправление на длинную ссылку.
     *
     * @param shortUrl кроткая ссылка
     * @return ответ
     */
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.convertToLongLink(shortUrl)))
                .build();
    }
}

