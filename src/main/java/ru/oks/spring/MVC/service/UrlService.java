package ru.oks.spring.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oks.spring.JPA.entity.Url;
import ru.oks.spring.JPA.repository.UrlRepository;
import ru.oks.spring.MVC.DTO.UrlDTO;

import javax.persistence.EntityNotFoundException;

@Service
public class UrlService {
    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public long convertToShortLink(UrlDTO urlDTO) {
        try {
            return urlRepository.getByLongUrl(urlDTO.getLongUrl()).getId();
        } catch (Exception e) {
            urlRepository.save(new Url(urlDTO.getLongUrl()));

            return urlRepository.getByLongUrl(urlDTO.getLongUrl()).getId();
        }
    }

    public String convertToLongLink(String shortLink) {
        long id = Long.parseLong(shortLink);

        try {
            Url link = urlRepository.getOne(id);
            return link.getLongUrl();
        } catch (EntityNotFoundException e) {
            return "There is no entity with " + shortLink;//TODO
        }
    }
}
