package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oks.spring.MVC.DTO.UrlDTO;
import ru.oks.spring.MVC.service.UrlService;

import java.net.URI;

/**
 * Контроллер ссылок.
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
    @PostMapping("/linkhash")
    public UrlDTO urlConvertToHash(@RequestBody UrlDTO request) {
        return new UrlDTO(urlService.convertToShortLink(request), null);
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

