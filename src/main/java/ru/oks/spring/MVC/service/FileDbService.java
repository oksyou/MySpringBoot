package ru.oks.spring.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.oks.spring.JPA.entity.Document;
import ru.oks.spring.JPA.repository.DocumentRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Сервис для работы с файлами в базе данных.
 */
@Service
public class FileDbService {
    @Autowired
    private DocumentRepository documentRepository;
    @Value("${path.db-files}")
    private String basePath;

    @Transactional
    public void save(Document document) {
        documentRepository.save(document);
    }

    /**
     * Поиск по названию.
     *
     * @param docName название
     * @return документ
     */
    @Transactional
    public Document findByDocName(String docName) {
        return documentRepository.findByDocName(docName);
    }

    /**
     * Загрузка в БД.
     *
     * @param file файл
     * @return ответ
     */
    @Transactional
    public ResponseEntity uploadInDb(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Document doc = new Document(fileName);
        try {
            doc.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        save(doc);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(basePath)
                .path("/download/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    /**
     * Загрузить файл из БД.
     *
     * @param request  запрос
     * @param fileName имя файла
     * @return ответ
     */
    @Transactional
    public ResponseEntity downloadFromDb(HttpServletRequest request, @PathVariable String fileName) {
        //получили документ
        Document document = findByDocName(fileName);
        String contentType;
        try {
            //тип документа
            contentType = request.getServletContext().getMimeType(fileName);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().header("Could not determine file type.").build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(document.getFile());
    }
}
