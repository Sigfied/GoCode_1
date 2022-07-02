package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.entity.UploadProperties;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.serviceImpl.UploadService;
import com.example.demo.tools.ExcelFileToJson;
import com.example.demo.tools.MathUtils;
import com.example.demo.tools.photoTools.DocAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
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
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/imageTodo")
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
    public  String uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        String path = uploadService.returnFilePath(file);
        log.info(path);
        @NotNull JSONArray jsonArray= ExcelFileToJson.tryExclTranslateToJson(path);
        return jsonArray.toString();
    }


    @RequestMapping("/createQuestionByExcel")
    public int createQuestionByExcel(@RequestParam("file") MultipartFile file,@RequestParam("tid") String tid) throws Exception {
        String path = uploadService.returnFilePath(file);
        log.info(path);
        @NotNull JSONArray jsonArray= ExcelFileToJson.tryExclTranslateToJson(path);
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Question question = new Question();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if(jsonObject!=null) {
                question.setQid(MathUtils.getPrimaryKey());
                question.setQtype(Integer.valueOf(jsonObject.getString("type")));
                question.setQdescribtion(jsonObject.getString("description"));
                question.setQinput(jsonObject.getString("input"));
                question.setQoutput(jsonObject.getString("output"));
                question.setTid(tid);
                question.setQpoint(new BigDecimal(jsonObject.getString("point")));
                questionList.add(question);
            }
        }
        log.info(questionList.toString());
        int i = questionMapper.insertBatchSomeColumn(questionList);
        log.info("插入条数："+i);
        return i;
    }

    @RequestMapping("/userInfoImage")
    public ResponseEntity<String> userInfoImage(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = "E:\\桌面\\实验报告\\实训\\GoCode_2_6-29\\GoCode\\static\\";
        return ResponseEntity.ok(uploadService.uploadImage(filePath,file));
    }

}
