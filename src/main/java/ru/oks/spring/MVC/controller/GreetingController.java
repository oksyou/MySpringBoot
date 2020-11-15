package ru.oks.spring.MVC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.oks.spring.MVC.DTO.IpLangBodyDTO;
import ru.oks.spring.MVC.DTO.TimeDifferenceDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, my friend";
    }

    @PostMapping("/simple_values")
    public IpLangBodyDTO simpleValues(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();

        Locale currentLocale = request.getLocale();
        String language = currentLocale.getLanguage();

        String body = "";
        try {
            BufferedReader bodyreader = request.getReader();
            body = bodyreader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            System.out.println(e);
        }

        IpLangBodyDTO temp = new IpLangBodyDTO();
        temp.ip = ipAddress;
        temp.lang = language;
        temp.body = body;

        return temp;
    }

    @GetMapping("/gettime")
    public TimeDifferenceDTO getTimeDifference(@RequestParam("time") long time) {

        TimeDifferenceDTO timeDifferenceDTO = new TimeDifferenceDTO();
        timeDifferenceDTO.currentTime = System.currentTimeMillis();
        timeDifferenceDTO.timediff = timeDifferenceDTO.currentTime - time;

        return timeDifferenceDTO;
    }

}
