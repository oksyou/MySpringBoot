package ru.oks.spring.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.oks.spring.MVC.DTO.UrlDTO;
import ru.oks.spring.MVC.controller.UrlController;
import ru.oks.spring.MVC.service.UrlService;

import static org.mockito.Mockito.when;

/**
 * Не очень-то и нужный тест на UrlController
 */
@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {

    @InjectMocks
    UrlController urlController;

    @Mock
    UrlService urlService;

    @Test
    public void testUrlConvertToHash(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UrlDTO urlDTO=new UrlDTO(0, "Съешь еще этих мягких французских булок да выпей чаю");
        when(urlService.convertToShortLink(urlDTO)).thenReturn((long) 1);
        UrlDTO newUrl1=urlController.urlConvertToHash(urlDTO);

        Assertions.assertEquals(1, newUrl1.getId());
        Assertions.assertNull(newUrl1.getLongUrl());
    }

    @Test
    public void testGetAndRedirect(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(urlService.convertToLongLink("1")).thenReturn("gaga");
        ResponseEntity responseEntity=urlController.getAndRedirect("1");

        Assertions.assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals("gaga", responseEntity.toString().substring(28, 32));
    }
}
