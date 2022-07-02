package com.example.demo.service.serviceImpl;

import com.example.demo.entity.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zw
 * @date 2022-06-26.
 */
@Service
public class UploadService {

    @Autowired
    private UploadProperties uploadProperties;

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        file.transferTo(new File(uploadProperties.getPath()+fileName));
        return fileName;
    }

    public String returnFilePath(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        return uploadProperties.getPath() + fileName;
    }

    public String uploadImage(String filepath,MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        file.transferTo(new File(filepath + fileName));
        return fileName;
    }
}
