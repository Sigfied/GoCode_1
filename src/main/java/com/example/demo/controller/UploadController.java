package com.example.demo.controller;

import com.example.demo.entity.UploadProperties;
import com.example.demo.service.serviceImpl.UploadService;
import com.example.demo.tools.photoTools.DocAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zw
 * @date 2022-06-26.
 */
@CrossOrigin
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadProperties uploadProperties;

    @PostMapping("/image")
    @ResponseBody
    public ResponseEntity<List> upload(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = uploadService.uploadImage(file);
        log.info(fileName);
        String filePath = uploadProperties.getPath()+fileName;
        String result = DocAnalysis.docAnalysis(filePath);
        log.info(result);
        JSONObject jsonObject = new JSONObject(result);
        JSONArray array = jsonObject.getJSONArray("results");
        List<String> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject words = (JSONObject) array.get(i);
            JSONObject words2 = words.getJSONObject("words");
            results.add(words2.getString("word"));
        }
        results.forEach(log::info);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(uploadService.uploadImage(file));
    }

}
