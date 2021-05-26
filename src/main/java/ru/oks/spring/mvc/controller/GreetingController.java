package ru.oks.spring.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.oks.spring.mvc.dto.IpLanguageBodyDto;
import ru.oks.spring.mvc.dto.TimeDifferenceDto;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Приветственный контроллер.
 */
@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, my friend";
    }

    /**
     * Получения id запроса, его языка и тела.
     *
     * @param request запрос
     * @return IpLangBodyDTO
     */
    @PostMapping("/simple_values")
    public IpLanguageBodyDto simpleValues(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();

        Locale currentLocale = request.getLocale();
        String language = currentLocale.getLanguage();

        String body = "";
        try {
            BufferedReader bodyReader = request.getReader();
            body = bodyReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            System.out.println(e);
        }

        var temp = new IpLanguageBodyDto();
        temp.setIp(ipAddress);
        temp.setLang(language);
        temp.setBody(body);

        return temp;
    }

    /**
     * Разница между текущим времени и времени в параметре.
     *
     * @param time время
     * @return разница
     */
    @GetMapping("/difference_time")
    public TimeDifferenceDto getTimeDifference(@RequestParam("time") long time) {
        var timeDifferenceDTO = new TimeDifferenceDto();
        timeDifferenceDTO.setCurrentTime(System.currentTimeMillis());
        timeDifferenceDTO.setTimeDifference(timeDifferenceDTO.getCurrentTime() - time);

        return timeDifferenceDTO;
    }

}
