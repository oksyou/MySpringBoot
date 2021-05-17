//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import ru.oks.spring.MVC.controller.FileDbController;
//import ru.oks.spring.configs.MatrixWebConfig;
//
//@WebAppConfiguration
//@ContextConfiguration(classes = { MatrixWebConfig.class, FileDbController.class })
////@RunWith(SpringRunner.class)
//public class FileDbServiceTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Test
//    public void testUploadInDb() {
//        MockMultipartFile file = new MockMultipartFile(
//                "file",
//                "hello.txt",
//                MediaType.TEXT_PLAIN_VALUE,
//                "Hello, World!".getBytes()
//        );
//        MockMvc mockMvc
//                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        mockMvc.perform(multipart("/upload").file(file))
//                .andExpect(status().isOk());
//    }
//}
