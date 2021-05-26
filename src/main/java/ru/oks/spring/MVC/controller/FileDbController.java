package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.oks.spring.MVC.service.FileDbService;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер для работы с файлами в БД.
 */
@RestController
@RequestMapping(value = "${path.db-files")
public class FileDbController {
    @Autowired
    private FileDbService fileDbService;

    /**
     * Загрузка файла в БД.
     *
     * @param file файл
     * @return ответ о выполнении загрузки
     */
    @PostMapping("/upload")
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
        return fileDbService.uploadInDb(file);
    }

    /**
     * Загрузка файла из БД.
     *
     * @param request Http запрос
     * @param fileName имя файла
     * @return ответ о загрузке
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity downloadFromDB(HttpServletRequest request, @PathVariable String fileName) {
        return fileDbService.downloadFromDb(request, fileName);
    }
}
