package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.oks.spring.MVC.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Рестконтроллер для работы с локальными файлами.
 */
@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * Загрузка файла локально.
     *
     * @param file файл
     * @return ответ о загрузке файла
     */
    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
        return fileService.upload(file);
    }

    /**
     * Загрузка нескольких файлов.
     *
     * @param files массив файлов
     * @return ответ о загрузке файлов
     */
    @PostMapping("/multi-upload")
    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(files)
                .stream()
                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
        return ResponseEntity.ok(fileDownloadUrls);
    }

    /**
     * Загрузка файла из локальной системы.
     *
     * @param request запрос
     * @param fileName название файла
     * @return ответ о загрузке
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity downloadFileFromLocal(HttpServletRequest request, @PathVariable String fileName) {
        return fileService.download(request, fileName);
    }

}
