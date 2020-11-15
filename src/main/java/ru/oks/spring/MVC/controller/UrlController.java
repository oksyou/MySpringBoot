package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oks.spring.MVC.DTO.UrlDTO;
import ru.oks.spring.MVC.service.UrlService;

import java.net.URI;

@RestController
@RequestMapping(value = "/url")

public class UrlController {
    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/linkhash")
    public UrlDTO urlConvertToHash(@RequestBody UrlDTO request) {
        return new UrlDTO(urlService.convertToShortLink(request), null);
    }

    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.convertToLongLink(shortUrl)))
                .build();
    }
}

