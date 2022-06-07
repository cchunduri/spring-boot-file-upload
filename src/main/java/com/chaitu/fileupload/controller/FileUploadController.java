package com.chaitu.fileupload.controller;

import com.chaitu.fileupload.service.FileStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 1) Upload File
 * 2) Store in server folder
 *
 * 1) Upload file
 * 2) Process CSV
 * 3) Extract Employee object
 * 4) Store in DB
 */

@Slf4j
@RestController
@AllArgsConstructor
public class FileUploadController {

    private FileStorageService fileStorageService;

    @GetMapping("/")
    public String sayHello() {
        log.info("Saying Hello....!");
        return "Hello World";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        var response = fileStorageService.saveFile(multipartFile);
        return ResponseEntity.ok(response);
    }
}
