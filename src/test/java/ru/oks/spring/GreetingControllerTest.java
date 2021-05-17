//package ru.oks.spring;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import ru.oks.spring.MVC.controller.GreetingController;
//
////@SpringBootTest
//@WebMvcTest()
//public class GreetingControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void sayHelloTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Hello, my friend"));
//    }
//}
