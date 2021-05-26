package ru.oks.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.oks.spring.jpa.entity.LocalDocument;
import ru.oks.spring.jpa.repository.LocalDocumentRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Сервис для работы с файлами на локальном компьютере.
 */
@Service
public class FileService {

    private final String BASE_PATH_FOR_UPLOAD = "D:\\example\\upload\\";

    @Value("${path.local-files}")
    private String basePath;

    @Autowired
    private LocalDocumentRepository localDocumentRepository;

    /**
     * Загрузка нового файла.
     *
     * @param file файл
     * @return ответ
     */
    public ResponseEntity upload(MultipartFile file) {
        String fileName;
        if (file.getOriginalFilename() != null) {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
        } else return null;
        Path path = Paths.get(BASE_PATH_FOR_UPLOAD + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        localDocumentRepository.save(new LocalDocument(fileName));
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(basePath)
                .path("/download/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    /**
     * Запрос файла.
     *
     * @param request  запрос
     * @param fileName название файла
     * @return ответ
     */
    public ResponseEntity download(HttpServletRequest request, @PathVariable String fileName) {
        if (localDocumentRepository.getOne(fileName) == null) {
            return null;
        }
        Path path = Paths.get(BASE_PATH_FOR_UPLOAD + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().header("Could not determine file type.").build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
