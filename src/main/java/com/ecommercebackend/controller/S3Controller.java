package com.ecommercebackend.controller;

import com.ecommercebackend.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/s3")
//@CrossOrigin(origins = "http://localhost:5173")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/presigned-url")
    public ResponseEntity<Map<String, String>> getPresignedUrl(@RequestBody Map<String, String> request) {
        String fileName = request.get("fileName");
        String fileType = request.get("fileType");

        // Generate unique key once
        String extension = "";
        if (fileName.contains(".")) {
            extension = fileName.substring(fileName.lastIndexOf("."));
        }
        String key = UUID.randomUUID().toString() + extension;

        String presignedUrl = s3Service.generatePresignedUrlWithKey(key, fileType);
        String fileUrl = s3Service.getFileUrlWithKey(key);

        return ResponseEntity.ok(Map.of(
                "presignedUrl", presignedUrl,
                "fileUrl", fileUrl
        ));
    }
}
