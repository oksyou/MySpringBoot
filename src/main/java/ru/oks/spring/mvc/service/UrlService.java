package ru.oks.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oks.spring.jpa.entity.Url;
import ru.oks.spring.jpa.repository.UrlRepository;
import ru.oks.spring.mvc.dto.UrlDto;

import javax.persistence.EntityNotFoundException;

/**
 * Сервис для работы с ссылками.
 */
@Service
public class UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * Конвертация в короткую ссылку.
     *
     * @param urlDTO длинная ссылка
     * @return короткая ссылка
     */
    public long convertToShortLink(UrlDto urlDTO) {
        try {
            return urlRepository.getByLongUrl(urlDTO.getLongUrl()).getId();
        } catch (Exception e) {
            urlRepository.save(new Url(urlDTO.getLongUrl()));

            return urlRepository.getByLongUrl(urlDTO.getLongUrl()).getId();
        }
    }

    /**
     * Конвертация в длинную ссылку.
     *
     * @param shortLink короткая ссылка
     * @return длинная ссылка
     */
    public String convertToLongLink(String shortLink) {
        long id = Long.parseLong(shortLink);

        try {
            Url link = urlRepository.getOne(id);
            return link.getLongUrl();
        } catch (EntityNotFoundException e) {
            return "There is no entity with " + shortLink;
        }
    }
}
