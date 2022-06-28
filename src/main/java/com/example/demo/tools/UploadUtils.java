package com.example.demo.tools;

import java.util.UUID;

/**
 * @author zw
 * @date 2022-06-26.
 */
public class UploadUtils {

    /**
     * 文件名称替换工具，将文件名称替换为随机名称
     * @param oldName 上传文件名字
     * @return 生成的新文件名
     */
    public static String generateFileName(String oldName) {
        String suffix = oldName.substring(oldName.lastIndexOf("."));

//        return
        return UUID.randomUUID().toString() + System.currentTimeMillis() + suffix;
    }
}
