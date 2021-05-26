package ru.oks.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.oks.spring.mvc.controller.GreetingController;

/**
 * Тестирование создания контроллера контекстом.
 */
@SpringBootTest
public class CreatingControllerTest {

    @Autowired
    GreetingController controller;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(controller);
    }
}
