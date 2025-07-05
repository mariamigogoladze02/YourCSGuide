package com.task.yourcsguide.controller;


import com.task.yourcsguide.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("rest/api/file")
public class FileController {
    private final FileService fileService;

    @PostMapping("upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.uploadTmp(file));
    }

    @GetMapping(value = "download/{objectName}")
    public ResponseEntity<Resource> download(@PathVariable String objectName) {
        Resource resource = fileService.download( objectName);

        String contentType = URLConnection.guessContentTypeFromName(resource.getFilename());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

}
