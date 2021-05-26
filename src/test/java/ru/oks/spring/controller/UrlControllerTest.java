package ru.oks.spring.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.oks.spring.mvc.dto.UrlDto;
import ru.oks.spring.mvc.controller.UrlController;
import ru.oks.spring.mvc.service.UrlService;

import static org.mockito.Mockito.when;

/**
 * Небольшой тест на UrlController
 */
@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {

    @InjectMocks
    UrlController urlController;

    @Mock
    UrlService urlService;

    @Test
    public void testUrlConvertToHash() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UrlDto urlDTO = new UrlDto(0, "Съешь еще этих мягких французских булок да выпей чаю");
        when(urlService.convertToShortLink(urlDTO)).thenReturn((long) 1);
        UrlDto newUrl1 = urlController.urlConvertToHash(urlDTO);

        Assertions.assertEquals(1, newUrl1.getId());
        Assertions.assertNull(newUrl1.getLongUrl());
    }

    @Test
    public void testGetAndRedirect() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(urlService.convertToLongLink("1")).thenReturn("gaga");
        ResponseEntity responseEntity = urlController.getAndRedirect("1");

        Assertions.assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals("gaga", responseEntity.toString().substring(28, 32));
    }
}
