package com.example.demo.controller;

import com.example.demo.service.serviceImpl.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zw
 * @date 2022-06-26.
 */
@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/image")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(uploadService.uploadImage(file));
    }

    @PostMapping("/excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(uploadService.uploadImage(file));
    }

}
