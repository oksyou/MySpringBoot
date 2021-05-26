package ru.oks.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.oks.spring.mvc.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * REST контроллер для работы с локальными файлами.
 */
@RestController
@RequestMapping(value = "${path.local-files}")
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
    @PostMapping("/multi_upload")
    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.stream(files)
                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
        return ResponseEntity.ok(fileDownloadUrls);
    }

    /**
     * Загрузка файла из локальной системы.
     *
     * @param request  запрос
     * @param fileName название файла
     * @return ответ о загрузке
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity downloadFileFromLocal(HttpServletRequest request, @PathVariable String fileName) {
        return fileService.download(request, fileName);
    }

}
