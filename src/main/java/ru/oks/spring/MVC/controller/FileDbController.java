package ru.oks.spring.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.oks.spring.MVC.service.FileDbService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/filesdb")
public class FileDbController {
    @Autowired
    private FileDbService fileDbService;

    @PostMapping("/upload")
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
        return fileDbService.uploadInDb(file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity downloadFromDB(HttpServletRequest request, @PathVariable String fileName) {
        return fileDbService.downloadFromDb(request, fileName);
    }
}
